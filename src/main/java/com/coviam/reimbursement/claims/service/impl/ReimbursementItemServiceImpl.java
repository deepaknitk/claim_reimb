package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.model.base.ReimbursementItemDto;
import com.coviam.reimbursement.claims.repository.ReimbursementItemRepository;
import com.coviam.reimbursement.claims.service.api.ReimbursementItemService;
import com.coviam.reimbursement.claims.service.api.ReimbursementService;
import com.coviam.reimbursement.claims.service.api.RestWebModelConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
@Service
public class ReimbursementItemServiceImpl implements ReimbursementItemService {

    private final Path rootLocation = Paths.get("/Users/payalmujavadiya/Desktop/Files");

  @Autowired
  private ReimbursementItemRepository reimbursementItemRepository;


  @Autowired
  private ReimbursementService reimbursementService;


  @Autowired
  private RestWebModelConverterService restWebModelConverterService;

  @Override
  public List<ReimbursementItem> saveOrUpdate(List<ReimbursementItem> reimbursementItemList, List<MultipartFile> fileList){

      this.init();
      List<ReimbursementItem> reimbursementItemListResponse =
          reimbursementItemRepository.save(reimbursementItemList);
      for (int index = 0; index < fileList.size(); index++) {
          try {
              Files.copy(fileList.get(index).getInputStream(), this.rootLocation.resolve(
                  "file_" + reimbursementItemListResponse.get(index) + "_"
                      + fileList.get(index).getOriginalFilename()));
          } catch (Exception e) {
              throw new RuntimeException("FAIL! -> message = " + e.getMessage());
          }
      }
      return reimbursementItemListResponse;
  }

  @Override
  public ReimbursementItem findByReimbursementItemByReimburesementItemId(Long rmbItemId) {
    ReimbursementItem reimbursementItem =
        reimbursementItemRepository.findByReimbursementItemId(rmbItemId);
    Long reimbursementId = reimbursementItem.getReimbursement().getReimbursementId();
    String description = reimbursementItem.getReimbursement().getStatusId().getStatusDescription();
    return reimbursementItemRepository.findByReimbursementItemId(rmbItemId);
  }

  @Override
  public List<ReimbursementItemDto> findByReimbursementItemListByReimburesementItemId(
      Long rmbItemId) {

    List<ReimbursementItem> reimbursementItems = reimbursementItemRepository
        .findByReimbursement(reimbursementService.findReimburesementByRmbItemId(rmbItemId));
    List<ReimbursementItemDto> reimbursementItemDtos = new ArrayList<>();
    for (ReimbursementItem reimbursementItem : reimbursementItems) {
      reimbursementItemDtos
          .add(restWebModelConverterService.convertRmbItemToRmbItemDto(reimbursementItem));
    }
    return reimbursementItemDtos;
  }

    @Override public Resource loadFile(String filename, Long ReimbursementItemId) {
        try {
            Path file = rootLocation.resolve("file_" + ReimbursementItemId + "_" + filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error! -> message = " + e.getMessage());
        }
    }

    @Override public void init() {
            try {
                Files.createDirectory(rootLocation);
            } catch (IOException e) {
                throw new RuntimeException("Could not initialize storage!");
            }
        }
    }


