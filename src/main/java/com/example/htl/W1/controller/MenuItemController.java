package com.example.htl.W1.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.htl.W1.model.BaseItem;
import com.example.htl.W1.model.CustomMenuItem;
import com.example.htl.W1.model.CustomMenuItemOptions;
import com.example.htl.W1.model.ItemType;
import com.example.htl.W1.model.Menu;
import com.example.htl.W1.model.MenuType;
import com.example.htl.W1.model.QuestionOptionType;
import com.example.htl.W1.service.BaseItemService;
import com.example.htl.W1.service.CustomMenuItemService;
import com.example.htl.W1.service.FixedMenuItemService;
import com.example.htl.W1.service.ItemTypeService;
import com.example.htl.W1.service.MenuService;
import com.example.htl.W1.service.MenuTypeService;
import com.example.htl.W1.service.QuestionOptionTypeService;
import com.example.model.User;

@Controller
public class MenuItemController extends BaseController{

	@Autowired
	ItemTypeService itemTypeService;

	@Autowired
	BaseItemService baseItemService;

	@Autowired
	MenuService menuService;

	@Autowired
	FixedMenuItemService fixedMenuItemService;
	
	@Autowired
	CustomMenuItemService customMenuItemService;

	@Autowired
	QuestionOptionTypeService questionOptionTypeService;
	
	@Autowired
	MenuTypeService menuTypeService;
	
	@RequestMapping(value="/admin/baseItem", method = RequestMethod.GET)
	public ModelAndView baseItem(Principal principal, @ModelAttribute("itemTypeSelected") String itemTypeSelected){
		ModelAndView modelAndView = new ModelAndView();

		User userObj = setupBaseParameter(modelAndView, principal);
		
		System.out.println("itemTypeId: "+itemTypeSelected);
		if(itemTypeSelected != null
				&& itemTypeSelected.trim().length()>0
				&& !itemTypeSelected.trim().equals("-1")){
			
			ItemType itemTypeObj = itemTypeService.getById(Long.parseLong(itemTypeSelected));
			List<BaseItem> baseItemList = baseItemService.getByAll(itemTypeObj);
			modelAndView.addObject("baseItemList", baseItemList);
			modelAndView.addObject("itemTypeSelected",itemTypeSelected);
		}else{
			List<BaseItem> baseItemList = baseItemService.getByAll();
			modelAndView.addObject("baseItemList", baseItemList);
			modelAndView.addObject("itemTypeSelected","");
		}
		
		
		List<ItemType> itemTypeList = itemTypeService.getByAll();
		modelAndView.addObject("itemTypeList", itemTypeList);
		
		BaseItem baseItemObj = new BaseItem();
		modelAndView.addObject("baseItem", baseItemObj);
		
		modelAndView.addObject("activeHeaderMenu", HeaderLinks.MANAGE_ITEM.getText());
		modelAndView.setViewName("admin/item/base_item");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/baseItemAdd", method = RequestMethod.POST)
	public ModelAndView baseItemAdd(Principal principal, @ModelAttribute("baseItem") @Validated BaseItem baseItemObj, BindingResult resultBunding){
		ModelAndView modelAndView = new ModelAndView();

		User userObj = setupBaseParameter(modelAndView, principal);
		
		//Validation with the for the same item already available or not.
		if(baseItemService.getByItemNameAndItemType(baseItemObj) != null){
			resultBunding.rejectValue("itemName", 
						"error.baseItem", 
						"Item name must be unique with same item type. \""+baseItemObj.getItemName()+" is already available with item type "+baseItemObj.getItemTypeObj().getItemTypeName()+"\"");

			List<BaseItem> baseItemList = baseItemService.getByAll(baseItemObj.getItemTypeObj());
			modelAndView.addObject("baseItemList", baseItemList);
			
		}else{
			baseItemService.saveBaseItem(baseItemObj);
			modelAndView.addObject("itemTypeSelected", baseItemObj.getItemTypeObj().getItemTypeId());

			List<BaseItem> baseItemList = baseItemService.getByAll(baseItemObj.getItemTypeObj());
			modelAndView.addObject("baseItemList", baseItemList);
			
			baseItemObj = new BaseItem();
			modelAndView.addObject("baseItem", baseItemObj);
		}
		
		List<ItemType> itemTypeList = itemTypeService.getByAll();
		modelAndView.addObject("itemTypeList", itemTypeList);
		
		modelAndView.addObject("activeHeaderMenu", HeaderLinks.MANAGE_ITEM.getText());
		modelAndView.setViewName("admin/item/base_item");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/baseItemEdit", method = RequestMethod.GET)
	public ModelAndView baseItemEdit(Principal principal, @ModelAttribute("baseItemId") String baseItemId){
		ModelAndView modelAndView = new ModelAndView();
		
		User userObj = setupBaseParameter(modelAndView, principal);
		
		List<BaseItem> baseItemList = baseItemService.getByAll();
		modelAndView.addObject("baseItemList", baseItemList);
		 
		List<ItemType> itemTypeList = itemTypeService.getByAll();
		modelAndView.addObject("itemTypeList", itemTypeList);
		
		BaseItem baseItemObj = baseItemService.getById(Long.parseLong(baseItemId));
		modelAndView.addObject("baseItem", baseItemObj);
		
		modelAndView.addObject("activeHeaderMenu", HeaderLinks.MANAGE_ITEM.getText());
		modelAndView.setViewName("admin/item/base_item");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/baseItemDelete", method = RequestMethod.GET)
	public ModelAndView baseItemDelete(Principal principal, @ModelAttribute("baseItemId") String baseItemId){
		ModelAndView modelAndView = new ModelAndView();
		
		User userObj = setupBaseParameter(modelAndView, principal);
		
		List<BaseItem> baseItemList = baseItemService.getByAll();
		modelAndView.addObject("baseItemList", baseItemList);
		 
		List<ItemType> itemTypeList = itemTypeService.getByAll();
		modelAndView.addObject("itemTypeList", itemTypeList);
		
		BaseItem baseItemObj = baseItemService.getById(Long.parseLong(baseItemId));
		
		try{
			baseItemService.deleteBaseItem(baseItemObj);
		}catch(Exception e){
			if(e.getMessage().contains("ConstraintViolationException")){
				System.out.println("Could not delete as it is already referenced.");
				modelAndView.addObject("errorMessage", "\""+baseItemObj.getItemName()+" can not be deleted. Because it is referenced from Menu Item. You must change/remove it.\"");
			}else{
				throw e;
			}
		}
		baseItemObj = new BaseItem();
		modelAndView.addObject("baseItem", baseItemObj);
		
		modelAndView.addObject("activeHeaderMenu", HeaderLinks.MANAGE_ITEM.getText());
		modelAndView.setViewName("admin/item/base_item");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/itemType", method = RequestMethod.GET)
	public ModelAndView itemType(Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		
		User userObj = setupBaseParameter(modelAndView, principal);
		
		ItemType itemTypeObj = new ItemType();
		modelAndView.addObject("itemType", itemTypeObj);
		
		List<ItemType> itemTypeList = itemTypeService.getByAll();
		modelAndView.addObject("itemTypeList", itemTypeList);

		modelAndView.addObject("activeHeaderMenu", HeaderLinks.MANAGE_ITEM_TYPE.getText());
		modelAndView.setViewName("admin/item/item_type");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/itemType", method = RequestMethod.POST)
	public ModelAndView itemTypeAdd(Principal principal, @ModelAttribute("itemType") @Valid ItemType itemTypeObj, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		
		User userObj = setupBaseParameter(modelAndView, principal);
		
		//validation for unique item add
		if(itemTypeService.getByItemType(itemTypeObj.getItemTypeName()) != null){
			bindingResult
			.rejectValue("itemTypeName", "error.itemType",
					"Item Type name must be unique. \""+itemTypeObj.getItemTypeName()+" is already added.\"");
		}else{
			itemTypeService.saveItemType(itemTypeObj);
			itemTypeObj = new ItemType();
			modelAndView.addObject("itemType", itemTypeObj);
		}
		
		List<ItemType> itemTypeList = itemTypeService.getByAll();
		modelAndView.addObject("itemTypeList", itemTypeList);

		modelAndView.addObject("activeHeaderMenu", HeaderLinks.MANAGE_ITEM_TYPE.getText());
		modelAndView.setViewName("admin/item/item_type");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/itemTypeDelete", method = RequestMethod.GET)
	public ModelAndView itemTypeDelete(Principal principal, @ModelAttribute("itemTypeId") String itemTypeId){
		ModelAndView modelAndView = new ModelAndView();

		User userObj = setupBaseParameter(modelAndView, principal);
		
		ItemType itemTypeObj = itemTypeService.getById(Long.parseLong(itemTypeId));
		
		try{
			itemTypeService.deleteItemType(itemTypeObj);
		}catch(Exception e){
			if(e.getMessage().contains("ConstraintViolationException")){
				System.out.println("Could not delete as it is already referenced.");
				modelAndView.addObject("errorMessage", "\""+itemTypeObj.getItemTypeName()+" can not be deleted. Because it is referenced from Base Item. You must change/remove item type for those items.\"");
			}else{
				throw e;
			}
		}
		
		List<ItemType> itemTypeList = itemTypeService.getByAll();
		modelAndView.addObject("itemTypeList", itemTypeList);
		
		ItemType itemTypeObj1 = new ItemType();
		modelAndView.addObject("itemType", itemTypeObj1);
		
		modelAndView.addObject("activeHeaderMenu", HeaderLinks.MANAGE_ITEM_TYPE.getText());
		modelAndView.setViewName("admin/item/item_type");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/itemTypeEdit", method = RequestMethod.GET)
	public ModelAndView itemTypeEditView(Principal principal, @ModelAttribute("itemTypeId") String itemTypeId){
		ModelAndView modelAndView = new ModelAndView();

		User userObj = setupBaseParameter(modelAndView, principal);
		
		ItemType itemTypeObj = itemTypeService.getById(Long.parseLong(itemTypeId));
		modelAndView.addObject("itemType", itemTypeObj);
		
		List<ItemType> itemTypeList = itemTypeService.getByAll();
		modelAndView.addObject("itemTypeList", itemTypeList);

		modelAndView.addObject("activeHeaderMenu", HeaderLinks.MANAGE_ITEM_TYPE.getText());
		modelAndView.setViewName("admin/item/item_type");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/menuCreate", method= RequestMethod.GET)
	public ModelAndView generateMenu(Principal principal){
		ModelAndView modelAndView = new ModelAndView();

		User userObj = setupBaseParameter(modelAndView, principal);
		
		List<MenuType> menuTypeList = menuTypeService.getByAll();
		modelAndView.addObject("menuTypeList", menuTypeList);
		
		Menu menuObj = new Menu();
		modelAndView.addObject("menuGenerate", menuObj);

		modelAndView.addObject("activeHeaderMenu", HeaderLinks.ADD_MENU.getText());
		modelAndView.setViewName("admin/item/menu_generator");
		return modelAndView;
	}
	
	//requestType - Normal, LoadForEdit, GenerateBaseItemList
	@RequestMapping(value="/admin/menuCreate", method= RequestMethod.POST)
	public ModelAndView generateMenuAddUpdate(Principal principal,
												@ModelAttribute("requestType") String requestType,
												@ModelAttribute("loadForEditmenuId") String loadForEditMenuId,
												@ModelAttribute("menuGenerate") Menu menuObj, 
												/*@ModelAttribute("changeMenuType") boolean changeMenuType,*/
												@ModelAttribute("itemTypeSelected") String itemTypeSelected,
												@ModelAttribute("custMenuItemOptionsObj") CustomMenuItemOptions custMenuItemOptionsObj){
		ModelAndView modelAndView = new ModelAndView();

		User userObj = setupBaseParameter(modelAndView, principal);
		
		System.out.println("requestType : "+requestType);
		if(requestType != null && requestType.equals("LoadForEdit")){
			menuObj = menuService.getByPK(Long.parseLong(loadForEditMenuId));
		}
		
		//Fixed or Custom Menu Radio Button
		List<MenuType> menuTypeList = menuTypeService.getByAll();
		modelAndView.addObject("menuTypeList", menuTypeList);

		//Checking which kind of menu is?
		if(menuObj != null && menuObj.getMenuType() != null){
			if(menuObj.getMenuType().getMenuTypeId() == 1){ //For Fixed Menu
				if(menuObj.getFixedMenuItemObj() != null
						&& menuObj.getFixedMenuItemObj().getFixedMenuDescription() != null){
					menuObj.getFixedMenuItemObj().setMenuItemReference(menuObj);
					
				}	
			}else if(menuObj.getMenuType().getMenuTypeId() == 2){ //For Custom Menu
				if(menuObj.getCustomMenuItemObj() != null
						&& menuObj.getCustomMenuItemObj().getCustomizationDescription() != null){
					menuObj.getCustomMenuItemObj().setMenuItemReference(menuObj);
				}	

				if(requestType.equals("GenerateBaseItemList")){
					if(itemTypeSelected != null
							&& itemTypeSelected.trim().length()>0){
						ItemType itemTypeObj = itemTypeService.getById(Long.parseLong(itemTypeSelected));
						List<BaseItem> baseItemList = baseItemService.getByAll(itemTypeObj);
						modelAndView.addObject("baseItemList", baseItemList);
						modelAndView.addObject("itemTypeSelected",itemTypeSelected);
					}else{
						modelAndView.addObject("itemTypeSelected","");
					}
					
				}
				List<QuestionOptionType> listQuestionOptionTypes = questionOptionTypeService.getByAll();
				modelAndView.addObject("listQuestionOptionTypes", listQuestionOptionTypes);
				
				if(custMenuItemOptionsObj == null){
					custMenuItemOptionsObj = new CustomMenuItemOptions();
				}
				modelAndView.addObject("custMenuItemOptionsObj", custMenuItemOptionsObj);
				
				//System.out.println("changeMenuType :: "+changeMenuType);
				
				
				List<ItemType> itemTypeList = itemTypeService.getByAll();
				modelAndView.addObject("itemTypeList", itemTypeList);
			}
		}
		
		
		//if(changeMenuType){
			/*if(menuObj.getMenuType().getMenuTypeId() == 1){
				CustomMenuItem  cmiObj = customMenuItemService.getByMenuItem(menuObj);
				if(cmiObj != null)
					customMenuItemService.delete(cmiObj);
			}else if(menuObj.getMenuType().getMenuTypeId() == 2){
				FixedMenuItems  fmiObj = fixedMenuItemService.getByMenuItem(menuObj);
				if(fmiObj != null)
					fixedMenuItemService.delete(fmiObj);
			}*/
		//}else{
			/*if(menuObj != null){
				if(menuObj.getMenuType().getMenuTypeId() == 1){
					if(menuObj.getFixedMenuItemObj() != null
							&& menuObj.getFixedMenuItemObj().getFixedMenuDescription() != null){
						menuObj.getFixedMenuItemObj().setMenuItemReference(menuObj);
						
					}	
				}else if(menuObj.getMenuType().getMenuTypeId() == 2){
					if(menuObj.getCustomMenuItemObj() != null
							&& menuObj.getCustomMenuItemObj().getCustomizationDescription() != null){
						menuObj.getCustomMenuItemObj().setMenuItemReference(menuObj);
					}	
				}
			}*/
		//}
		
		
		//Custom Menu Item Object settting
		if(custMenuItemOptionsObj != null
				&& custMenuItemOptionsObj.getQuestionForChoose() != null
				&& custMenuItemOptionsObj.getQuestionForChoose().trim().length()>0
				&& custMenuItemOptionsObj.getQuestionOptionType() != null
				&& custMenuItemOptionsObj.getListOfAvailableOptions() != null
				&& custMenuItemOptionsObj.getListOfAvailableOptions().size()>0){
			
			//CustomMenuItem customMenuItemObj = customMenuItemService.getByMenuItem(menuObj);
			menuObj = menuService.getByPK(menuObj.getMenuId());
			CustomMenuItem customMenuItemObj = menuObj.getCustomMenuItemObj();
			
			customMenuItemObj.addMenuItemQuestions(custMenuItemOptionsObj);
			//custMenuItemOptionsObj.setCustomMenuItemObj(customMenuItemObj);
			modelAndView.addObject("custMenuItemOptionsObj", new CustomMenuItemOptions());
			modelAndView.addObject("itemTypeSelected","");
		}
	
		if(requestType != null && requestType.equals("Normal")){
			menuObj = menuService.save(menuObj);
		}
		menuObj = menuService.getByPK(menuObj.getMenuId());

		modelAndView.addObject("menuGenerate", menuObj);
		
		modelAndView.addObject("activeHeaderMenu", HeaderLinks.ADD_MENU.getText());
		modelAndView.setViewName("admin/item/menu_generator");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/menuCreatorList", method=RequestMethod.GET)
	public ModelAndView listGeneratorMenu(Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		
		User userObj = setupBaseParameter(modelAndView, principal);
		
		List<MenuType> listMenuType = menuTypeService.getByAll();
		modelAndView.addObject("menuTypeList", listMenuType);
		
		List<Menu> listMenu = menuService.getByAll();
		modelAndView.addObject("menuList", listMenu);
		
		modelAndView.addObject("activeHeaderMenu", HeaderLinks.LIST_MENU.getText());
		modelAndView.setViewName("admin/item/menu_generator_list");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/menuCreatorList", method=RequestMethod.POST)
	public ModelAndView listGeneratorMenu(Principal principal, @ModelAttribute("menuTypeSelected") String menuType){
		ModelAndView modelAndView = new ModelAndView();
		
		User userObj = setupBaseParameter(modelAndView, principal);
		
		List<MenuType> listMenuType = menuTypeService.getByAll();
		modelAndView.addObject("menuTypeList", listMenuType);

		modelAndView.addObject("menuTypeSelected", menuType);
		
		MenuType menuTypeObj = menuTypeService.getByPk(Long.parseLong(menuType));
		
		if(menuTypeObj != null){
			List<Menu> listMenu = menuService.getByAll(menuTypeObj);
			modelAndView.addObject("menuList", listMenu);
		}else{
			List<Menu> listMenu = menuService.getByAll();
			modelAndView.addObject("menuList", listMenu);
		}
		
		modelAndView.addObject("activeHeaderMenu", HeaderLinks.LIST_MENU.getText());
		modelAndView.setViewName("admin/item/menu_generator_list");
		return modelAndView;
	}
}