package com.veso.util;

public class MySMSTool {

	public MySMSTool() {
		super();
		// TODO Auto-generated constructor stub
	}
	 public static String buildMobileOperator(String userId) {
	        String mobileOperator = null;
	        if (userId.startsWith("8491") || userId.startsWith("091") || userId.startsWith("91")
	        		||userId.startsWith("94")||userId.startsWith("094")||userId.startsWith("8494")
	        		||userId.startsWith("123")||userId.startsWith("0123")||userId.startsWith("84123")) {
	            mobileOperator = "GPC";
	        } else if (userId.startsWith("8490") || userId.startsWith("090") || userId.startsWith("90")
	        		||userId.startsWith("8493") || userId.startsWith("093") || userId.startsWith("93")
	        		||userId.startsWith("84126") || userId.startsWith("0126") || userId.startsWith("126")
	        		|| userId.startsWith("0122")|| userId.startsWith("84122")|| userId.startsWith("122")) {
	            	mobileOperator = "VMS";
	        } else if (userId.startsWith("8498") || userId.startsWith("098") || userId.startsWith("98") 
	        		||userId.startsWith("8497") || userId.startsWith("097") || userId.startsWith("97") 
	        		|| userId.startsWith("0168") || userId.startsWith("84168") ||userId.startsWith("168")
	        		|| userId.startsWith("0169") || userId.startsWith("84169")|| userId.startsWith("169")
	        		|| userId.startsWith("0166")|| userId.startsWith("84166")|| userId.startsWith("166")) {
	            mobileOperator = "VIETEL";
	        } else if (userId.startsWith("8495") || userId.startsWith("095") || userId.startsWith("95")) {
	            mobileOperator = "SFONE";
	        } else if (userId.startsWith("8492") || userId.startsWith("092") || userId.startsWith("92")) {
	            mobileOperator = "HNT";
	        
	        }else if (userId.startsWith("8496") || userId.startsWith("096") || userId.startsWith("96")) {
	            mobileOperator = "EVN";
	        }else if (userId.length() > 6){
	            String shortcode = userId.substring(userId.length() - 6);
	            if (shortcode.startsWith("27") || userId.startsWith("84427") || userId.startsWith("84827") || shortcode.startsWith("28") || userId.startsWith("84428") || userId.startsWith("84828") || shortcode.startsWith("29") || userId.startsWith("84429") || userId.startsWith("84829"))
	            {
	              mobileOperator = "VIETEL";
	            }
	            else if ((shortcode.startsWith("50")) || (userId.startsWith("84450")) || (userId.startsWith("84850")) || (shortcode.startsWith("60")) || (userId.startsWith("84460")) || (userId.startsWith("84860")) || (shortcode.startsWith("70")) || (userId.startsWith("84470")) || (userId.startsWith("84870")) || (shortcode.startsWith("350")) || (shortcode.startsWith("360")) || (shortcode.startsWith("370")))
	            {
	              mobileOperator = "GPC";
	            }
	        } else mobileOperator = "VIETEL";   
	           
	        return mobileOperator;
	 }
    public static String[] extractMobileNumber(String info) {
        if (info == null || info.length() <= 10) return null;

        String mobile = null;
        int index = info.indexOf("09");
        String tempMobile = null;

        while(index > 0 && info.length() >= (index + 10)) {
            tempMobile = info.substring(index, index + 10);
            if (StringTool.isNumberic(tempMobile)) {
                mobile = tempMobile;
                info = info.substring(0, index) + " " + info.substring(index + 10);
                break;
            }
            index = info.indexOf("09", index + 2);
        }
        String[] result = null;
        String[] numbers=new String[]{"0168","0169","0122","0123"};
        if(mobile==null){
        	for(int i=0;i<numbers.length;i++){
        		index=info.indexOf(numbers[i]);
        		 while(index > 0 && info.length() >= (index + 11)) {
        	            tempMobile = info.substring(index, index + 11);
        	            if (StringTool.isNumberic(tempMobile)) {
        	                mobile = tempMobile;
        	                info = info.substring(0, index) + " " + info.substring(index + 11);
        	                break;
        	            }
        	            index = info.indexOf(numbers[i], index + numbers[i].length());
        	      }
        		 if(mobile!=null)break;
        	}
        }
        if (mobile != null) {
            result = new String[2];
            result[0] = mobile;
            result[1] = info;
        }
        return result;
    }
    public static boolean isValidMobileNumber(String userId) {
        if (userId == null || "".equals(userId) || !StringTool.isNumberic(userId)||userId.length()<=7) {
            return false;
        }
       return true;
    }
    public static String filter(String s) {
        if(s==null||s.equals("")){
        	return "";
        }
        String result=null;
        if(s.length()<=160) result=s;
        if(s.length()>160) result= s.substring(0,160);
        System.out.println(result.length());
        return result;
    }


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(MySMSTool.filter("Co len naeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeoeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"));

	}

}
