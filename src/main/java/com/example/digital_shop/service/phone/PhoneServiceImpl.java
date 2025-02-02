package com.example.digital_shop.service.phone;


import com.example.digital_shop.domain.dto.PhoneDto;
import com.example.digital_shop.entity.product.PhoneEntity;
import com.example.digital_shop.exception.DataNotFoundException;
import com.example.digital_shop.repository.phone.PhoneRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService{

    private final PhoneRepository phoneRepository;
    private final ModelMapper modelMapper;



    @Override
    public PhoneEntity add(PhoneDto phoneDto, UUID userId, Integer amount, String token) {
        PhoneEntity phoneEntity = modelMapper.map(phoneDto,PhoneEntity.class);
        phoneEntity.setUserId(userId);
        PhoneEntity save = phoneRepository.save(phoneEntity);
//        addInventory(save,amount,token);
        return save;
    }

//    public void addInventory(PhoneEntity save, Integer amount,String token){
//        InventoryDto inventoryDto = new InventoryDto(save.getId(),amount);
//        HttpHeaders httpHeaders=new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        token=token.substring(7);
//        httpHeaders.setBearerAuth(token);
//        HttpEntity<InventoryDto> entity=new HttpEntity<>(inventoryDto,httpHeaders);
//        ResponseEntity<String> exchange = restTemplate.exchange(URI.create(inventoryServiceUrl + "/add"),
//                HttpMethod.POST, entity, String.class);
//        String body = exchange.getBody();
//    }


    public List<PhoneEntity> getAllPhone(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        List<PhoneEntity> content = phoneRepository.findAll(pageable).getContent();
        if(content.isEmpty()){
            throw new DataNotFoundException("Phones not found");
        }
        return content;
    }

    public List<PhoneEntity> search(int page, int size, String model) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page, size, sort);
        return phoneRepository.searchPhoneEntitiesByModelContainingIgnoreCase(model, pageable);
    }




    public Boolean deleteById(UUID productId, UUID userId,String token) {
        PhoneEntity phoneNotFound = phoneRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("Phone not found"));
        if (phoneNotFound.getUserId().equals(userId)) {
            phoneRepository.deleteById(productId);
//            deleteInventory(productId, token);
            return true;
        }
        throw new DataNotFoundException("Phone not found");
    }

//    public void deleteInventory(UUID productId,String token){
//        HttpHeaders httpHeaders=new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        token=token.substring(7);
//        httpHeaders.setBearerAuth(token);
//        HttpEntity<UUID> entity=new HttpEntity<>(productId,httpHeaders);
//        ResponseEntity<String> exchange = restTemplate.exchange(URI.create(inventoryServiceUrl +"/"+ productId + "/delete"),
//                HttpMethod.DELETE, entity, String.class);
//        String body = exchange.getBody();
//
//    }



    public PhoneEntity update(PhoneDto phoneDto, UUID phoneId, UUID userId) {
        PhoneEntity phoneEntity = phoneRepository.findById(phoneId)
                .orElseThrow(() -> new DataNotFoundException("Phone not found"));
        if (phoneEntity.getUserId().equals(userId)) {
            modelMapper.map(phoneDto, phoneEntity);
            return phoneRepository.save(phoneEntity);
        }
        throw new DataNotFoundException("Phone not found");
    }






}
