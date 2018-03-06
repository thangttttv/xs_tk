package com.veso.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.veso.bean.Chot;
import com.veso.bean.KetQuaMienBacBean;
import com.veso.bean.KetQuaMienNamBean;
import com.veso.bean.KetQuaMienTrungBean;
import com.veso.bean.UserPoint;
import com.veso.bean.UserPointLog;
import com.veso.dao.KetQuaMienBacDAO;
import com.veso.dao.KetQuaMienNamDAO;
import com.veso.dao.KetQuaMienTrungDAO;
import com.veso.dao.PointDAO;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class ChotSoThread {
	public void chotMienBac(){
		PointDAO pointDAO = new PointDAO();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
		String ngay_quay = dateFormat.format(calendar.getTime());
		//ngay_quay = "2018-03-05";
		List<Chot> chots = pointDAO.getChotByNgayQuay(ngay_quay, 1);
		KetQuaMienBacDAO mienBacDAO = new KetQuaMienBacDAO();
		Vector<KetQuaMienBacBean> ketQuaMienBacBeans;
		try {
			ketQuaMienBacBeans = mienBacDAO.findByNgayQuay(new java.sql.Date(dateFormat.parse(ngay_quay).getTime()));
			KetQuaMienBacBean ketqua = ketQuaMienBacBeans.get(0);
			HashMap<String,Integer> mapBoSo = this.createMapBoSoMienBac(ketqua);
			
			for(Chot chot: chots){
				System.out.println("Nguoi choi"+ chot.user_id + "");
				
				String loto = chot.loto;
				String loto_dacbiet = chot.loto_dac_biet;
			
				JSONObject json = (JSONObject) JSONSerializer.toJSON(loto);
				Iterator<String> iterator = json.keys();
				
				// check loto
				JSONObject jsonAfterCheck = new JSONObject();
				while(iterator.hasNext()){
					String boso = iterator.next();
					if(mapBoSo.containsKey(boso)){
						String key = boso;
							System.out.println("-->Bo so "+ boso + "-->Co ra "+mapBoSo.get(key));
							// log point
							UserPointLog userPoint = new UserPointLog();
							userPoint.user_id = chot.user_id;
							userPoint.point = 20*4*mapBoSo.get(key);
							userPoint.reference_id = chot.id;
							userPoint.type = 1;
							userPoint.description = "Cong point chot so: "+ boso + "- ngay: "+ chot.ngay_quay+"- Tinh:"+chot.province_id;
							if(pointDAO.saveUserPointLog(userPoint)){
								// Tinh tong point
								if(!pointDAO.checkUserPoint(chot.user_id)){
									UserPoint uPoint = new UserPoint();
									uPoint.point = userPoint.point;
									uPoint.user_id = chot.user_id;
									pointDAO.saveUserPoint(uPoint);
								}else{
									pointDAO.changePoint(chot.user_id, userPoint.point);
								}
							}
					}
					jsonAfterCheck.put(boso, mapBoSo.get(boso)!=null?mapBoSo.get(boso):0);
					
					
				}
				
				String json_loto = jsonAfterCheck.toString();
				System.out.println(jsonAfterCheck.toString());
				
				// check loto db
				json = (JSONObject) JSONSerializer.toJSON(loto_dacbiet);
				JSONObject jsonAfterCheckDB = new JSONObject();
				iterator = json.keys();
				while(iterator.hasNext()){
					String boso = iterator.next();
						if(boso.compareToIgnoreCase(ketqua.getGiai_dacbiet().substring(ketqua.getGiai_dacbiet().length()-2))==0){
							System.out.println("-->Loto Dac Biet "+ boso + "-->Co ra");
							
							// log point
							UserPointLog userPoint = new UserPointLog();
							userPoint.user_id = chot.user_id;
							userPoint.point = 20*70;
							userPoint.reference_id = chot.id;
							userPoint.type = 1;
							userPoint.description = "Cong point chot so db: "+ boso + "- ngay: "+ chot.ngay_quay+"- Tinh:"+chot.province_id;
							if(pointDAO.saveUserPointLog(userPoint)){
								// Tinh tong point
								if(!pointDAO.checkUserPoint(chot.user_id)){
									UserPoint uPoint = new UserPoint();
									uPoint.point = userPoint.point;
									uPoint.user_id = chot.user_id;
									pointDAO.saveUserPoint(uPoint);
								}else{
									pointDAO.changePoint(chot.user_id, userPoint.point);
								}
							}
							jsonAfterCheckDB.put(boso, 1);
						}else{
							jsonAfterCheckDB.put(boso, 0);
						}
					
						
				}
				String json_loto_db = jsonAfterCheckDB.toString();
				// Update bang x_chot
				pointDAO.updateChot(chot.id, json_loto, json_loto_db);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void chotMienNam(){
		// Get Danh sach Tinh Quay Mien Nam
		PointDAO pointDAO = new PointDAO();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
		String ngay_quay = dateFormat.format(calendar.getTime());
		//ngay_quay = "2018-02-22";
		
		KetQuaMienNamDAO mienNamDAO = new KetQuaMienNamDAO();
		
		try {
			Vector<KetQuaMienNamBean> kqList = mienNamDAO.findByNgayQuay(new java.sql.Date(dateFormat.parse(ngay_quay).getTime()));
			int i = 0;
			while(i<kqList.size()){
				KetQuaMienNamBean ketqua = kqList.get(i);
				List<Chot> chots = pointDAO.getChotByNgayQuay(ngay_quay, ketqua.getProvince_id());
				// Tao map loto
				HashMap<String,Integer> mapBoSo = this.createMapBoSoMienNam(ketqua);
				
				for(Chot chot: chots){
					System.out.println("Nguoi choi"+ chot.user_id + "");
					
					String loto = chot.loto;
					String loto_dacbiet = chot.loto_dac_biet;
				
					JSONObject json = (JSONObject) JSONSerializer.toJSON(loto);
					Iterator<String> iterator = json.keys();
					// check loto
					JSONObject jsonAfterCheck = new JSONObject();
					while(iterator.hasNext()){
						String boso = iterator.next();
						if(mapBoSo.containsKey(boso)){
							String key = boso;
								System.out.println("-->Bo so "+ boso + "-->Co ra "+mapBoSo.get(key));
								// log point
								UserPointLog userPoint = new UserPointLog();
								userPoint.user_id = chot.user_id;
								userPoint.point = 20*4*mapBoSo.get(key);
								userPoint.reference_id = chot.id;
								userPoint.type = 1;
								userPoint.description = "Cong point chot so: "+ boso + "- ngay: "+ chot.ngay_quay+"- Tinh:"+chot.province_id;
								if(pointDAO.saveUserPointLog(userPoint)){
									// Tinh tong point
									if(!pointDAO.checkUserPoint(chot.user_id)){
										UserPoint uPoint = new UserPoint();
										uPoint.point = userPoint.point;
										uPoint.user_id = chot.user_id;
										pointDAO.saveUserPoint(uPoint);
									}else{
										pointDAO.changePoint(chot.user_id, userPoint.point);
									}
								}
						}
						jsonAfterCheck.put(boso, mapBoSo.get(boso)!=null?mapBoSo.get(boso):0);
					}
					System.out.println(jsonAfterCheck.toString());

					// check loto db
					json = (JSONObject) JSONSerializer.toJSON(loto_dacbiet);
					JSONObject jsonAfterCheckDB = new JSONObject();
					iterator = json.keys();
					while(iterator.hasNext()){
						String boso = iterator.next();
							if(boso.compareToIgnoreCase(ketqua.getGiai_dacbiet().substring(ketqua.getGiai_dacbiet().length()-2))==0){
								System.out.println("-->Loto Dac Biet "+ boso + "-->Co ra");
								
								// log point
								UserPointLog userPoint = new UserPointLog();
								userPoint.user_id = chot.user_id;
								userPoint.point = 20*70;
								userPoint.reference_id = chot.id;
								userPoint.type = 1;
								userPoint.description = "Cong point chot so db: "+ boso + "- ngay: "+ chot.ngay_quay+"- Tinh:"+chot.province_id;
								if(pointDAO.saveUserPointLog(userPoint)){
									// Tinh tong point
									if(!pointDAO.checkUserPoint(chot.user_id)){
										UserPoint uPoint = new UserPoint();
										uPoint.point = userPoint.point;
										uPoint.user_id = chot.user_id;
										pointDAO.saveUserPoint(uPoint);
									}else{
										pointDAO.changePoint(chot.user_id, userPoint.point);
									}
								}
								jsonAfterCheckDB.put(boso, 1);
							}else{
								jsonAfterCheckDB.put(boso, 0);
							}
						
							
					}
					// Update bang x_chot
					String json_loto_db = jsonAfterCheckDB.toString();
					pointDAO.updateChot(chot.id, jsonAfterCheck.toString(), json_loto_db);
				}
				i++;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void chotMienTrung(){
		// Get Danh sach Tinh Quay Mien Nam
		PointDAO pointDAO = new PointDAO();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
		String ngay_quay = dateFormat.format(calendar.getTime());
		//ngay_quay = "2018-02-22";
		
		KetQuaMienTrungDAO mienTrungDAO = new KetQuaMienTrungDAO();
		
		try {
			Vector<KetQuaMienTrungBean> kqList = mienTrungDAO.findByNgayQuay(new java.sql.Date(dateFormat.parse(ngay_quay).getTime()));
			int i = 0;
			while(i<kqList.size()){
				KetQuaMienTrungBean ketqua = kqList.get(i);
				List<Chot> chots = pointDAO.getChotByNgayQuay(ngay_quay, ketqua.getProvince_id());
				// Tao map loto
				HashMap<String,Integer> mapBoSo = this.createMapBoSoMienTrung(ketqua);
				
				for(Chot chot: chots){
					System.out.println("Nguoi choi"+ chot.user_id + "");
					
					String loto = chot.loto;
					String loto_dacbiet = chot.loto_dac_biet;
				
					JSONObject json = (JSONObject) JSONSerializer.toJSON(loto);
					Iterator<String> iterator = json.keys();
					// check loto
					JSONObject jsonAfterCheck = new JSONObject();
					while(iterator.hasNext()){
						String boso = iterator.next();
						if(mapBoSo.containsKey(boso)){
							String key = boso;
								System.out.println("-->Bo so "+ boso + "-->Co ra "+mapBoSo.get(key));
								// log point
								UserPointLog userPoint = new UserPointLog();
								userPoint.user_id = chot.user_id;
								userPoint.point = 20*4*mapBoSo.get(key);
								userPoint.reference_id = chot.id;
								userPoint.type = 1;
								userPoint.description = "Cong point chot so: "+ boso + "- ngay: "+ chot.ngay_quay+"- Tinh:"+chot.province_id;
								if(pointDAO.saveUserPointLog(userPoint)){
									// Tinh tong point
									if(!pointDAO.checkUserPoint(chot.user_id)){
										UserPoint uPoint = new UserPoint();
										uPoint.point = userPoint.point;
										uPoint.user_id = chot.user_id;
										pointDAO.saveUserPoint(uPoint);
									}else{
										pointDAO.changePoint(chot.user_id, userPoint.point);
									}
								}
						}
						jsonAfterCheck.put(boso, mapBoSo.get(boso)!=null?mapBoSo.get(boso):0);
					}
					System.out.println(jsonAfterCheck.toString());

					// check loto db
					json = (JSONObject) JSONSerializer.toJSON(loto_dacbiet);
					JSONObject jsonAfterCheckDB = new JSONObject();
					iterator = json.keys();
					while(iterator.hasNext()){
						String boso = iterator.next();
							if(boso.compareToIgnoreCase(ketqua.getGiai_dacbiet().substring(ketqua.getGiai_dacbiet().length()-2))==0){
								System.out.println("-->Loto Dac Biet "+ boso + "-->Co ra");
								
								// log point
								UserPointLog userPoint = new UserPointLog();
								userPoint.user_id = chot.user_id;
								userPoint.point = 20*70;
								userPoint.reference_id = chot.id;
								userPoint.type = 1;
								userPoint.description = "Cong point chot so db: "+ boso + "- ngay: "+ chot.ngay_quay+"- Tinh:"+chot.province_id;
								if(pointDAO.saveUserPointLog(userPoint)){
									// Tinh tong point
									if(!pointDAO.checkUserPoint(chot.user_id)){
										UserPoint uPoint = new UserPoint();
										uPoint.point = userPoint.point;
										uPoint.user_id = chot.user_id;
										pointDAO.saveUserPoint(uPoint);
									}else{
										pointDAO.changePoint(chot.user_id, userPoint.point);
									}
								}
								jsonAfterCheckDB.put(boso, 1);
							}else{
								jsonAfterCheckDB.put(boso, 0);
							}
						
							
					}
					// Update bang x_chot
					String json_loto_db = jsonAfterCheckDB.toString();
					pointDAO.updateChot(chot.id, jsonAfterCheck.toString(), json_loto_db);
				}
				i++;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private HashMap<String, Integer> createMapBoSoMienNam(KetQuaMienNamBean ketqua){
		List<String> bosos  = new ArrayList<String>();
		HashMap<String,Integer> mapBoSo = new HashMap<String, Integer>();
		
		bosos.add(ketqua.getGiai_dacbiet().substring(ketqua.getGiai_dacbiet().length()-2));
		bosos.add(ketqua.getGiai_nhat().substring(ketqua.getGiai_nhat().length()-2));
		bosos.add(ketqua.getGiai_nhi().substring(ketqua.getGiai_nhi().length()-2));
		
		bosos.add(ketqua.getGiai_ba_1().substring(ketqua.getGiai_ba_1().length()-2));
		bosos.add(ketqua.getGiai_ba_2().substring(ketqua.getGiai_ba_2().length()-2));
		
		bosos.add(ketqua.getGiai_tu_1().substring(ketqua.getGiai_tu_1().length()-2));
		bosos.add(ketqua.getGiai_tu_2().substring(ketqua.getGiai_tu_2().length()-2));
		bosos.add(ketqua.getGiai_tu_3().substring(ketqua.getGiai_tu_3().length()-2));
		bosos.add(ketqua.getGiai_tu_4().substring(ketqua.getGiai_tu_4().length()-2));
		bosos.add(ketqua.getGiai_tu_5().substring(ketqua.getGiai_tu_5().length()-2));
		bosos.add(ketqua.getGiai_tu_6().substring(ketqua.getGiai_tu_6().length()-2));
		bosos.add(ketqua.getGiai_tu_7().substring(ketqua.getGiai_tu_7().length()-2));
		
		bosos.add(ketqua.getGiai_nam().substring(ketqua.getGiai_nam().length()-2));
		
		bosos.add(ketqua.getGiai_sau_1().substring(ketqua.getGiai_sau_1().length()-2));
		bosos.add(ketqua.getGiai_sau_2().substring(ketqua.getGiai_sau_2().length()-2));
		bosos.add(ketqua.getGiai_sau_3().substring(ketqua.getGiai_sau_3().length()-2));
		
		bosos.add(ketqua.getGiai_bay().substring(ketqua.getGiai_bay().length()-2));
		bosos.add(ketqua.getGiai_tam().substring(ketqua.getGiai_tam().length()-2));
		
		for(String boso : bosos){
			if(mapBoSo.containsKey(boso)){
				mapBoSo.put(boso, mapBoSo.get(boso)+1);
			}else{
				mapBoSo.put(boso, 1);
			}
		}
		return mapBoSo;
	}
	
	private HashMap<String, Integer> createMapBoSoMienTrung(KetQuaMienTrungBean ketqua){
		List<String> bosos  = new ArrayList<String>();
		HashMap<String,Integer> mapBoSo = new HashMap<String, Integer>();
		
		bosos.add(ketqua.getGiai_dacbiet().substring(ketqua.getGiai_dacbiet().length()-2));
		bosos.add(ketqua.getGiai_nhat().substring(ketqua.getGiai_nhat().length()-2));
		bosos.add(ketqua.getGiai_nhi().substring(ketqua.getGiai_nhi().length()-2));
		
		bosos.add(ketqua.getGiai_ba_1().substring(ketqua.getGiai_ba_1().length()-2));
		bosos.add(ketqua.getGiai_ba_2().substring(ketqua.getGiai_ba_2().length()-2));
		
		bosos.add(ketqua.getGiai_tu_1().substring(ketqua.getGiai_tu_1().length()-2));
		bosos.add(ketqua.getGiai_tu_2().substring(ketqua.getGiai_tu_2().length()-2));
		bosos.add(ketqua.getGiai_tu_3().substring(ketqua.getGiai_tu_3().length()-2));
		bosos.add(ketqua.getGiai_tu_4().substring(ketqua.getGiai_tu_4().length()-2));
		bosos.add(ketqua.getGiai_tu_5().substring(ketqua.getGiai_tu_5().length()-2));
		bosos.add(ketqua.getGiai_tu_6().substring(ketqua.getGiai_tu_6().length()-2));
		bosos.add(ketqua.getGiai_tu_7().substring(ketqua.getGiai_tu_7().length()-2));
		
		bosos.add(ketqua.getGiai_nam().substring(ketqua.getGiai_nam().length()-2));
		
		bosos.add(ketqua.getGiai_sau_1().substring(ketqua.getGiai_sau_1().length()-2));
		bosos.add(ketqua.getGiai_sau_2().substring(ketqua.getGiai_sau_2().length()-2));
		bosos.add(ketqua.getGiai_sau_3().substring(ketqua.getGiai_sau_3().length()-2));
		
		bosos.add(ketqua.getGiai_bay().substring(ketqua.getGiai_bay().length()-2));
		bosos.add(ketqua.getGiai_tam().substring(ketqua.getGiai_tam().length()-2));
		
		for(String boso : bosos){
			if(mapBoSo.containsKey(boso)){
				mapBoSo.put(boso, mapBoSo.get(boso)+1);
			}else{
				mapBoSo.put(boso, 1);
			}
		}
		return mapBoSo;
	}
	
	private HashMap<String, Integer> createMapBoSoMienBac(KetQuaMienBacBean ketqua){
			List<String> bosos  = new ArrayList<String>();
			HashMap<String,Integer> mapBoSo = new HashMap<String, Integer>();
		
			bosos.add(ketqua.getGiai_dacbiet().substring(ketqua.getGiai_dacbiet().length()-2));
			bosos.add(ketqua.getGiai_nhat().substring(ketqua.getGiai_nhat().length()-2));
			bosos.add(ketqua.getGiai_nhi_1().substring(ketqua.getGiai_nhi_1().length()-2));
			bosos.add(ketqua.getGiai_nhi_2().substring(ketqua.getGiai_nhi_2().length()-2));
			
			bosos.add(ketqua.getGiai_ba_1().substring(ketqua.getGiai_ba_1().length()-2));
			bosos.add(ketqua.getGiai_ba_2().substring(ketqua.getGiai_ba_2().length()-2));
			bosos.add(ketqua.getGiai_ba_3().substring(ketqua.getGiai_ba_3().length()-2));
			bosos.add(ketqua.getGiai_ba_4().substring(ketqua.getGiai_ba_4().length()-2));
			bosos.add(ketqua.getGiai_ba_5().substring(ketqua.getGiai_ba_5().length()-2));
			bosos.add(ketqua.getGiai_ba_6().substring(ketqua.getGiai_ba_6().length()-2));
			
			bosos.add(ketqua.getGiai_tu_1().substring(ketqua.getGiai_tu_1().length()-2));
			bosos.add(ketqua.getGiai_tu_2().substring(ketqua.getGiai_tu_2().length()-2));
			bosos.add(ketqua.getGiai_tu_3().substring(ketqua.getGiai_tu_3().length()-2));
			bosos.add(ketqua.getGiai_tu_4().substring(ketqua.getGiai_tu_4().length()-2));
			
			bosos.add(ketqua.getGiai_nam_1().substring(ketqua.getGiai_nam_1().length()-2));
			bosos.add(ketqua.getGiai_nam_2().substring(ketqua.getGiai_nam_2().length()-2));
			bosos.add(ketqua.getGiai_nam_3().substring(ketqua.getGiai_nam_3().length()-2));
			bosos.add(ketqua.getGiai_nam_4().substring(ketqua.getGiai_nam_4().length()-2));
			bosos.add(ketqua.getGiai_nam_5().substring(ketqua.getGiai_nam_5().length()-2));
			bosos.add(ketqua.getGiai_nam_6().substring(ketqua.getGiai_nam_6().length()-2));
			
			bosos.add(ketqua.getGiai_sau_1().substring(ketqua.getGiai_sau_1().length()-2));
			bosos.add(ketqua.getGiai_sau_2().substring(ketqua.getGiai_sau_2().length()-2));
			bosos.add(ketqua.getGiai_sau_3().substring(ketqua.getGiai_sau_3().length()-2));
			
			bosos.add(ketqua.getGiai_bay_1().substring(ketqua.getGiai_bay_1().length()-2));
			bosos.add(ketqua.getGiai_bay_2().substring(ketqua.getGiai_bay_2().length()-2));
			bosos.add(ketqua.getGiai_bay_3().substring(ketqua.getGiai_bay_3().length()-2));
			bosos.add(ketqua.getGiai_bay_4().substring(ketqua.getGiai_bay_4().length()-2));
			
			for(String boso : bosos){
				if(mapBoSo.containsKey(boso)){
					mapBoSo.put(boso, mapBoSo.get(boso)+1);
				}else{
					mapBoSo.put(boso, 1);
				}
			}
			
		return mapBoSo;
	}
	
	public static void main(String[] args) {
		ChotSoThread chotSoThread = new ChotSoThread();
		chotSoThread.chotMienNam();
	}
}
