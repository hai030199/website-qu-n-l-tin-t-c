package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.INewService;

public class NewService implements INewService{
	@Inject
	private INewDAO newDAO;
	
	@Inject
	private ICategoryDAO categoryDAO;
	

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		return newDAO.findByCategoryId(categoryId);
		
	}


	@Override
	public NewModel save(NewModel newModel) {
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel category =categoryDAO.findOneByCode(newModel.getCategoryCode());
		newModel.setCategoryId(category.getId());
		Long newId=newDAO.save(newModel);
		return newDAO.findOne(newId);
	}


	@Override
	public NewModel update(NewModel updateNew) {
		NewModel oldNew =newDAO.findOne(updateNew.getId());
		updateNew.setCreatedDate(oldNew.getCreatedDate());
		updateNew.setCreatedby(oldNew.getCreatedby());
		updateNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel category =categoryDAO.findOneByCode(updateNew.getCategoryCode());
		updateNew.setCategoryId(category.getId());
		newDAO.update(updateNew);
		return newDAO.findOne(updateNew.getId());
	}


	

	@Override
	public void delete(long[] ids) {
		for(long id:ids)
		{
			//1.delete comment trước (khóa ngoại new id)
			//2.delete news
			newDAO.delete(id);
			
		}
		
	}


	@Override
	public List<NewModel> findAll(Pageble pageble) {
		
		return newDAO.findAll(pageble);
	}


	@Override
	public int getTotalItem() {
		return newDAO.getTotalItem();
	}


	@Override
	public NewModel findOne(long id) {
		NewModel newModel=newDAO.findOne(id);//lấy ra id cần sửa
		CategoryModel categoryModel=categoryDAO.findOne(newModel.getCategoryId());//lấy ra id theo categoryId  trong new và lưu vào categoryModel
		newModel.setCategoryCode(categoryModel.getCode());//lấy ra code trong categoryModel và lưu vào CategoryCode trong newModel
		return newModel;
	}


	

}
