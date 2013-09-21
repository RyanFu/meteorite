/**
 * 
 */
package com.meteorite.taobao.api;

import com.meteorite.core.util.UString;
import com.meteorite.taobao.TbItemCat;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Item;
import com.taobao.api.domain.ItemCat;
import com.taobao.api.domain.Product;
import com.taobao.api.domain.SellerCat;
import com.taobao.api.request.*;
import com.taobao.api.response.*;

import java.util.*;

/**
 * �Ա���ƷAPI
 * 
 * @author wei_jc
 * @since webshop 1.00
 */
public class TbItemFacade {
//	private static final Logger log = LogFactory.getLogger(TbItemFacade.class);
	
	private TaobaoClient client;
	private String sessionKey;
	
	public TbItemFacade(TaobaoClient client, String sessionKey) {
		this.client = client;
		this.sessionKey = sessionKey;
	}
	
	/**
	 * ���һ����Ʒ
	 * 
	 * @param item ��Ʒ��Ϣ
	 * @throws com.taobao.api.ApiException
	 * @throws com.taobao.api.ApiException
	 * @since webshop 1.00
	 */
	public ItemAddResponse addItem(Item item) throws ApiException {
//		log.debug("��ʼ����Ա���Ʒ��" + item.getTitle());
		ItemAddRequest req = new ItemAddRequest();
		// ������
		req.setNum(item.getNum()); // ��Ʒ����
		req.setPrice(item.getPrice());  // ��Ʒ�۸�
		req.setType(item.getType());  // ��������
		req.setStuffStatus(item.getStuffStatus());  // �¾ɳ̶�
		req.setTitle(item.getTitle());  // ��������
		req.setDesc(item.getDesc());  // ��������
//		req.setLocationState(item.getLocationState());  // ���ڵ�ʡ��
//		req.setLocationCity(item.getLocationCity());  // ���ڵس���
		req.setCid(item.getCid());  // Ҷ����Ŀid

		// ��ѡ��
		req.setSellerCids(item.getSellerCids());  // ��Ʒ�����ĵ�����Ŀ�б�
		req.setApproveStatus(item.getApproveStatus());  // ��Ʒ�ϴ����״̬
		req.setDesc(item.getDesc());  // ��������
		req.setFreightPayer(item.getFreightPayer());  // �˷ѳе���ʽ
		req.setValidThru(item.getValidThru());  // ��Ч��
		req.setHasInvoice(item.getHasInvoice());  // �Ƿ��з�Ʊ
		req.setHasWarranty(item.getHasWarranty());  // �Ƿ��б���
		req.setHasShowcase(item.getHasShowcase());  // �����Ƽ�
		req.setHasDiscount(item.getHasDiscount());  // ֧�ֻ�Ա����
		req.setListTime(item.getListTime());  // ��ʱ�ϼ�ʱ��
		req.setIncrement(item.getIncrement());  // �Ӽ۷���
//		req.setImage(item.getImage());  // ��Ʒ��ͼƬ
		req.setAuctionPoint(item.getAuctionPoint());  // ��Ʒ�Ļ��ַ������
		req.setInputPids(item.getInputPids());  // ����ֵ����
//		req.setSkuProperties(item.getSkuProperties());  // ���µ�Sku�����Դ�
//		req.setSkuQuantities(item.getSkuQuantities());  // Sku��������
//		req.setSkuPrices(item.getSkuPrices());  // Sku�ļ۸�
//		req.setSkuOuterIds(item.getSkuOuterIds());  // Sku���ⲿid��
//		req.setLang(item.getLang());  // ��Ʒ���ֵ��ַ���
		req.setOuterId(item.getOuterId());  // �̼ұ��룬���ֶε���󳤶���512���ֽ�
		req.setProductId(item.getProductId());  // ��Ʒ�����Ĳ�ƷID(B�̼ҷ�����Ʒ��Ҫ��)
//		req.setPicPath(item.getPicPath());  // ��Ʒ��ͼ��Ҫ������ͼƬ�ռ�����url
		req.setAutoFill(item.getAutoFill());  // ������Ʒ����
		req.setInputStr(item.getInputStr());  // �û������������������������ֵ
		req.setIsTaobao(item.getIsTaobao());  // �Ƿ����Ա�����ʾ
		req.setIsEx(item.getIsEx());  // �Ƿ��������ʾ
		req.setIs3D(item.getIs3D());  // �Ƿ���3D
		req.setSellPromise(item.getSellPromise());  // �Ƿ��ŵ�˻�������!������Ʒ�������ô���!
		req.setAfterSaleId(item.getAfterSaleId());  // �ۺ�˵��ģ��id
		req.setCodPostageId(item.getCodPostageId());  // ��Ϊ���������˷�ģ���ID
		req.setProps(item.getProps());  // ��Ʒ�����б�
		req.setPostageId(item.getPostageId());  // �����������˷�ģ��ID
		req.setSubStock(item.getSubStock());  // �̼��Ƿ�֧�����¼����
		req.setItemWeight(item.getItemWeight());  // ��Ʒ������
		req.setIsLightningConsignment(item.getIsLightningConsignment());  // ʵ�����緢��

		return client.execute(req, sessionKey);
	}

	/**
	 * �ϴ�һ����Ʒ
	 *
	 * @param prod
	 * @return
	 * @throws com.taobao.api.ApiException
	 */
	public ProductAddResponse addProduct(Product prod) throws ApiException {
//		log.debug("��ʼ����Ա���Ʒ��" + prod.getName());
		ProductAddRequest req = new ProductAddRequest();

		req.setCid(prod.getCid());
		req.setName(prod.getName());
		req.setPrice(prod.getPrice());
//		req.setImage(prod.getImage());
		req.setCustomerProps(prod.getCustomerProps());

		return client.execute(req, sessionKey);
	}

	/**
	 * ��ȡ��ǰ�Ự�û����г����е���Ʒ�б�
	 *
	 * @throws com.taobao.api.ApiException
	 */
	public List<Item> getOnsaleItems() throws ApiException {
//		log.debug("��ʼ��ѯ�Ա�������������Ʒ");
		List<Item> result = new ArrayList<Item>();

		ItemsOnsaleGetRequest req = new ItemsOnsaleGetRequest();
		req.setFields("num_iid");
		req.setPageSize(200L);
		long pageNo = 1;

		while(true) {
			req.setPageNo(pageNo++);
			ItemsOnsaleGetResponse res = client.execute(req, sessionKey);
			if(!res.isSuccess() || res.getItems() == null) {
				break;
			}

			result.addAll(res.getItems());
		}

		return result;
	}

	/**
	 * �õ���ǰ�Ự�û�����е���Ʒ�б�
	 *
	 * @param banner �����ֶΡ���ѡֵ:
	 *                      regular_shelved(��ʱ�ϼ�) never_on_shelf(��δ�ϼ�) off_shelf(���¼ܵ�)
	 *                      for_shelved(�ȴ������ϼ�) sold_out(ȫ������) violation_off_shelf(Υ���¼ܵ�)
	 *                      Ĭ�ϲ�ѯ����for_shelved(�ȴ������ϼ�)���״̬����Ʒ
	 * @return
	 * @throws com.taobao.api.ApiException
	 */
	public List<Item> getInventoryItems(String banner) throws ApiException {
//		log.debug("��ʼ��ѯ�Ա���ǰ�Ự�û�����е���Ʒ�б�, banner = " + banner);
		List<Item> result = new ArrayList<Item>();

		ItemsInventoryGetRequest req = new ItemsInventoryGetRequest();
		req.setFields("num_iid");
		req.setBanner(UString.isEmpty(banner) ? "for_shelved" : banner);
		req.setPageSize(200L);
		long pageNo = 1;

		while(true) {
			req.setPageNo(pageNo++);
			ItemsInventoryGetResponse res = client.execute(req, sessionKey);
			if(!res.isSuccess() || res.getItems() == null) {
				break;
			}

			result.addAll(res.getItems());
		}

		return result;
	}

	/**
	 * ��ȡ���е���Ʒ�б�Ids�����������е���Ʒ��ȫ���������Ʒ�����¼ܵ���Ʒ��Υ���¼ܵ���Ʒ
	 *
	 * @return
	 * @throws com.taobao.api.ApiException
	 */
	public Set<Long> getAllListItemIds() throws ApiException {
		Set<Long> set = new HashSet<Long>();
		// ȡ�����е���Ʒ
		List<Item> onSaleList = getOnsaleItems();
		for(Item item : onSaleList) {
			set.add(item.getNumIid());
		}
		// ȡȫ���������Ʒ
		List<Item> soldOutList = getInventoryItems("sold_out");
		for(Item item : soldOutList) {
			set.add(item.getNumIid());
		}
		// �ȴ������ϼ�
		List<Item> offShelfList = getInventoryItems("for_shelved");
		for(Item item : offShelfList) {
			set.add(item.getNumIid());
		}
		// ȡΥ���¼ܵĵ���Ʒ
		List<Item> violationOffShelfList = getInventoryItems("violation_off_shelf");
		for(Item item : violationOffShelfList) {
			set.add(item.getNumIid());
		}

		return set;
	}

	/**
	 * ��ȡ����Ʒ�б�
	 *
	 * @param set numIid����
	 * @return
	 * @throws com.taobao.api.ApiException
	 */
	public List<Item> getListItems(Set<Long> set) throws ApiException {
		List<Item> result = new ArrayList<Item>();

		StringBuilder sb = new StringBuilder();
		int i = 1;
		for(Iterator<Long> iterator = set.iterator(); iterator.hasNext();) {
			Long id = iterator.next();
			sb.append(id).append(",");
			if(i++ % 20 == 0) {
				if(sb.toString().endsWith(",")) {
					sb.deleteCharAt(sb.length() - 1);
				}

				result.addAll(getListItems(sb.toString()));
				sb = new StringBuilder();
			}
		}
		// ȡʣ���
		if(sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		result.addAll(getListItems(sb.toString()));

		return result;
	}

	/**
	 * ������ȡ��Ʒ��Ϣ
	 *
	 * @param numIids ��Ʒ����id�б����num_iid�ö��Ÿ�����һ�β�����20����
	 * @return
	 * @throws com.taobao.api.ApiException
	 */
	public List<Item> getListItems(String numIids) throws ApiException {
		List<Item> result = new ArrayList<Item>();
		if(UString.isEmpty(numIids)) {
			return result;
		}
		ItemsListGetRequest req = new ItemsListGetRequest();
		String fields = "approve_status,num_iid,cid,title,pic_url,num,props,list_time,price,outer_id,seller_cids,input_pids,input_str,desc,product_id";
		req.setFields(fields);
		req.setNumIids(numIids);
		ItemsListGetResponse res = client.execute(req, sessionKey);
		if(res.isSuccess()) {
			result.addAll(res.getItems());
		}

		return result;
	}

	/**
	 * ��ȡһ����Ʒ����Ϣ ,���ַ�ʽ: ����product_id����ѯ�� ����cid��props����ѯ
	 *
	 * @param productId
	 * @param cid
	 * @param props
	 * @return
	 * @throws com.taobao.api.ApiException
	 */
	public Product getProduct(Long productId, Long cid, String props) throws ApiException {
		ProductGetRequest req = new ProductGetRequest();
		req.setFields("product_id,price,props_str,binds_str");
		if(productId != null) {
			req.setProductId(productId);
		} else {
			req.setCid(cid);
			req.setProps(props);
		}
		ProductGetResponse res = client.execute(req, sessionKey);
		if(res.isSuccess()) {
			return res.getProduct();
		}

		return null;
	}

	/**
	 * ������Ʒ��Ϣ����漰һ�ڼۣ�
	 *
	 * @param info
	 * @return
	 * @throws com.taobao.api.ApiException
	 */
	public ItemUpdateResponse updateItem(Item info) throws ApiException {
		ItemUpdateRequest req = new ItemUpdateRequest();

		req.setNumIid(info.getNumIid());
		req.setNum(info.getNum());
		req.setPrice(info.getPrice());

		return client.execute(req, sessionKey);
	}

	/**
	 * һ�ڼ���Ʒ�ϼ�
	 *
	 * @param info
	 * @return
	 * @throws com.taobao.api.ApiException
	 */
	public ItemUpdateListingResponse listingUpdateItem(Item info) throws ApiException {
		ItemUpdateListingRequest req = new ItemUpdateListingRequest();

		req.setNumIid(info.getNumIid());
		req.setNum(info.getNum());

		return client.execute(req, sessionKey);
	}

	/**
	 * ������Ʒ�¼�
	 *
	 * @param numIid
	 * @return
	 * @throws com.taobao.api.ApiException
	 */
	public ItemUpdateDelistingResponse delistingUpdateItem(Long numIid) throws ApiException {
		ItemUpdateDelistingRequest req = new ItemUpdateDelistingRequest();
		req.setNumIid(numIid);
		return client.execute(req, sessionKey);
	}

	/**
	 * ɾ���Ա��ϵ���Ʒ
	 *
	 * @param numIid
	 * @return
	 * @throws com.taobao.api.ApiException
	 */
	public ItemDeleteResponse deleteItem(Long numIid) throws ApiException {
		ItemDeleteRequest req = new ItemDeleteRequest();
		req.setNumIid(numIid);
		return client.execute(req, sessionKey);
	}

	/**
	 * �ݹ���÷��ش���Ŀ�����з���
	 *
	 * @return
	 * @throws com.taobao.api.ApiException
	 */
	public void getItemCats(TbItemCat parent) throws ApiException {
		ItemcatsGetRequest req = new ItemcatsGetRequest();
		req.setFields("cid, parent_cid, name, is_parent, status, sort_order");
		req.setParentCid(parent.getCid());
		ItemcatsGetResponse res = client.execute(req, sessionKey);
		if (res.isSuccess()) {
			if(res.getItemCats() != null && res.getItemCats().size() > 0) {
				for(ItemCat itemCat : res.getItemCats()) {
//					DefaultCategory cat = new DefaultCategory(itemCat.getCid(), itemCat.getParentCid(), itemCat.getName());
                    TbItemCat cat = new TbItemCat();
                    cat.setCid(itemCat.getCid());
                    cat.setParentCid(itemCat.getParentCid());
                    cat.setName(itemCat.getName());
                    cat.setParent(parent);
                    cat.setIsParent(itemCat.getIsParent());
                    cat.setStatus(itemCat.getStatus());
                    cat.setSortOrder(itemCat.getSortOrder());
					parent.addChild(cat);
					if(itemCat.getIsParent()) {
						getItemCats(cat);
					}
				}
			}
		}
	}

	/**
	 * ������ҵ��Զ�����Ʒ����
	 *
	 * @param venderName
	 * @return
	 * @throws com.taobao.api.ApiException
	 */
	public List<ItemCat> getSellerCats(String venderName) throws ApiException {
		List<ItemCat> result = new ArrayList<ItemCat>();

		SellercatsListGetRequest req = new SellercatsListGetRequest();
		req.setNick(venderName);
		SellercatsListGetResponse res = client.execute(req, sessionKey);
		if(res.isSuccess()) {
			if(res.getSellerCats() != null && res.getSellerCats().size() > 0) {
				for(SellerCat sellerCat : res.getSellerCats()) {
					/*DefaultCategory cat;
					if(sellerCat.getParentCid() == 0) {
						cat = new DefaultCategory(sellerCat.getCid() + "", "root", sellerCat.getName());
					} else {
						cat = new DefaultCategory(sellerCat.getCid(), sellerCat.getParentCid(), sellerCat.getName());
					}
					result.add(cat);*/
				}
			}
		}

		return result;
	}

	/**
	 *
	 * @param numIid
	 * @param images
	 * @throws com.taobao.api.ApiException
	 */
	public void uploadImg(Long numIid, byte[] images, boolean isMajor) throws ApiException {
		ItemImgUploadRequest req = new ItemImgUploadRequest();
		req.setNumIid(numIid);
		req.setIsMajor(isMajor);
//		String fileName = "����" + "." + UtilImage.getImageType(images);
//		req.setImage(new FileItem(fileName, images));

		ItemImgUploadResponse res = client.execute(req, sessionKey);
		if(!res.isSuccess()) {
//			log.error(String.format("�ϴ��Ա�ͼƬʧ�ܡ�%s��\n, %s", numIid + "", ErrorMsgFactory.getTbErrorMsg(res)));
		}
	}

    /**
     * ��ȡ������Ʒ����ϸ��Ϣ ����δ��¼ʱֻ�ܻ�������Ʒ�Ĺ������ݣ����ҵ�¼����Ի�ȡ��Ʒ����������
     *
     * @param numIid
     * @return
     * @throws com.taobao.api.ApiException
     */
    public ItemGetResponse getItem(Long numIid) throws ApiException {
        ItemGetRequest req = new ItemGetRequest();
        req.setNumIid(numIid);
        req.setFields("detail_url,num_iid,title,nick,type,cid,seller_cids,props,input_pids,input_str,desc,pic_url,num,valid_thru,list_time,delist_time,stuff_status,location,price,post_fee,express_fee,ems_fee,has_discount,freight_payer,has_invoice,has_warranty,has_showcase,modified,increment,approve_status,postage_id,product_id,auction_point,property_alias,item_img,prop_img,sku,video,outer_id,is_virtual");

        return client.execute(req, sessionKey);
    }
}
