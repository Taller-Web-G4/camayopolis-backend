package com.camayopolis.service.implementation;

import com.camayopolis.persistence.entity.ComboEntity;
import com.camayopolis.persistence.entity.ComboItemEntity;
import com.camayopolis.persistence.entity.ProductEntity;
import com.camayopolis.persistence.repository.IComboItemRepository;
import com.camayopolis.persistence.repository.IComboRepository;
import com.camayopolis.persistence.repository.IProductRepository;
import com.camayopolis.presentation.dto.ComboItemRequestDto;
import com.camayopolis.presentation.dto.ComboItemResponseDto;
import com.camayopolis.service.interfaces.IComboItemService;
import com.camayopolis.util.mapper.IComboItemRequestMapper;
import com.camayopolis.util.mapper.IComboItemResponseMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ComboItemServiceImpl implements IComboItemService {
    private final IComboItemRepository comboItemRepository;
    private final IComboItemRequestMapper comboItemRequestMapper;
    private final IComboItemResponseMapper comboItemResponseMapper;
    private final IProductRepository productRepository;
    private final IComboRepository comboRepository;

    public ComboItemServiceImpl(IComboItemRepository comboItemRepository, IComboItemRequestMapper comboItemRequestMapper, IComboItemResponseMapper comboItemResponseMapper, IProductRepository productRepository, IComboRepository comboRepository) {
        this.comboItemRepository = comboItemRepository;
        this.comboItemRequestMapper = comboItemRequestMapper;
        this.comboItemResponseMapper = comboItemResponseMapper;
        this.productRepository = productRepository;
        this.comboRepository = comboRepository;
    }

    @Override
    public List<ComboItemResponseDto> getAllComboItems() {
        List<ComboItemEntity> comboItemEntities = this.comboItemRepository.findAll();
        return comboItemResponseMapper.toDto(comboItemEntities);
    }

    @Override
    public Optional<ComboItemResponseDto> getComboItemById(Integer id) {
        if (!this.existsById(id)) {
            return Optional.empty();
        }
        Optional<ComboItemEntity> comboItemEntity = this.comboItemRepository.findById(id);
        return comboItemEntity.map(comboItemResponseMapper::toDto);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.comboItemRepository.existsById(id);
    }

    @Override
    public Optional<ComboItemRequestDto> createComboItem(ComboItemRequestDto comboItemRequestDto) {
        if (!productRepository.existsById(comboItemRequestDto.productId()) || !comboRepository.existsById(comboItemRequestDto.comboId())) {
            return Optional.empty();
        }

        ProductEntity productEntity = this.productRepository.findById(comboItemRequestDto.productId())
                .orElseThrow(() -> new IllegalStateException("Product not found despite check"));
        ComboEntity comboEntity = this.comboRepository.findById(comboItemRequestDto.comboId())
                .orElseThrow(() -> new IllegalStateException("Combo not found despite check"));

        BigDecimal totalProductPrice = productEntity.getPrdPrecio().multiply(BigDecimal.valueOf(comboItemRequestDto.quantity()));
        comboEntity.setCmbPrecio(comboEntity.getCmbPrecio().add(totalProductPrice));
        comboRepository.save(comboEntity);

        ComboItemEntity comboItemEntity = this.comboItemRequestMapper.toEntity(comboItemRequestDto);
        ComboItemEntity savedEntity = this.comboItemRepository.save(comboItemEntity);
        return Optional.of(this.comboItemRequestMapper.toDto(savedEntity));
    }

    @Override
    public Optional<ComboItemRequestDto> updateComboItem(Integer id, ComboItemRequestDto comboItemRequestDto) {
        if (!comboItemRepository.existsById(id)) {
            return Optional.empty();
        }

        if (!productRepository.existsById(comboItemRequestDto.productId())) {
            return Optional.empty();
        }

        ProductEntity productEntity = this.productRepository.findById(comboItemRequestDto.productId())
                .orElseThrow(() -> new IllegalStateException("Product not found despite check"));
        ComboItemEntity originalComboItemEntity = this.comboItemRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("ComboItem not found despite check"));
        Integer originalComboId = originalComboItemEntity.getCmb().getId();
        ComboEntity comboEntity = this.comboRepository.findById(originalComboId)
                .orElseThrow(() -> new IllegalStateException("Combo not found despite check"));

        BigDecimal newComboPrice;

        if (Objects.equals(comboItemRequestDto.productId(), originalComboItemEntity.getPrd().getId())) {
            if (Objects.equals(comboItemRequestDto.quantity(), originalComboItemEntity.getCmbiCantidad())) {
                return Optional.of(comboItemRequestMapper.toDto(originalComboItemEntity));
            }
            BigDecimal quantityDifference;
            if (comboItemRequestDto.quantity() < originalComboItemEntity.getCmbiCantidad()) {
                quantityDifference = BigDecimal.valueOf(originalComboItemEntity.getCmbiCantidad() - comboItemRequestDto.quantity());
                newComboPrice = comboEntity.getCmbPrecio().subtract(productEntity.getPrdPrecio().multiply(quantityDifference));
            } else {
                quantityDifference = BigDecimal.valueOf(comboItemRequestDto.quantity() - originalComboItemEntity.getCmbiCantidad());
                newComboPrice = comboEntity.getCmbPrecio().add(productEntity.getPrdPrecio().multiply(quantityDifference));
            }
            comboEntity.setCmbPrecio(newComboPrice);
        } else {
            newComboPrice = comboEntity.getCmbPrecio()
                    .subtract(originalComboItemEntity.getPrd().getPrdPrecio().multiply(BigDecimal.valueOf(originalComboItemEntity.getCmbiCantidad())))
                    .add(productEntity.getPrdPrecio().multiply(BigDecimal.valueOf(comboItemRequestDto.quantity())));
            comboEntity.setCmbPrecio(newComboPrice);
        }

        comboRepository.save(comboEntity);
        ComboItemEntity comboItemEntity = comboItemRequestMapper.toEntity(comboItemRequestDto);
        comboItemEntity.setId(id);
        comboItemEntity.setCmb(comboEntity);

        return Optional.of(comboItemRequestMapper.toDto(this.comboItemRepository.save(comboItemEntity)));
    }

    @Override
    public void deleteComboItem(Integer id) {
        if (!this.existsById(id)) {
            return;
        }

        ComboItemEntity comboItemEntity = this.comboItemRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("ComboItem not found despite check"));
        Integer quantityOfComboItemWithSameComboId = this.comboItemRepository.countComboItemEntitiesByCmb_Id(comboItemEntity.getCmb().getId());

        this.comboItemRepository.deleteById(id);

        if (quantityOfComboItemWithSameComboId == 1) {
            ComboEntity comboEntity = this.comboRepository.findById(comboItemEntity.getCmb().getId())
                    .orElseThrow(() -> new IllegalStateException("Combo not found despite check"));
            this.comboRepository.deleteById(comboEntity.getId());
        } else {
            ProductEntity productEntity = this.productRepository.findById(comboItemEntity.getPrd().getId())
                    .orElseThrow(() -> new IllegalStateException("Product not found despite check"));
            ComboEntity comboEntity = this.comboRepository.findById(comboItemEntity.getCmb().getId())
                    .orElseThrow(() -> new IllegalStateException("Combo not found despite check"));
            BigDecimal totalProductPrice = productEntity.getPrdPrecio().multiply(BigDecimal.valueOf(comboItemEntity.getCmbiCantidad()));
            comboEntity.setCmbPrecio(comboEntity.getCmbPrecio().subtract(totalProductPrice));
            this.comboRepository.save(comboEntity);
        }
    }
}
