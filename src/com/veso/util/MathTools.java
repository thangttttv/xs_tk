
package com.veso.util;

public class MathTools {

	public static float round(float value, int afterPoint) {
		if (afterPoint < 0) return value;
		float result = value;
		try {
			String sTemp = String.valueOf(value);
			if (sTemp.indexOf(".") < 0) {
				StringBuffer buffer = new StringBuffer("");
				buffer.append(sTemp+".");
				for (int i = 0; i<afterPoint; i++) {
					buffer.append("0");
				}
				result = Float.parseFloat(buffer.toString());
			} else {
				String sTemp1 = sTemp.substring(0, sTemp.indexOf("."));
				String sTemp2 = sTemp.substring(sTemp.indexOf(".")+1);
				
				if (sTemp2 != null) {
					if (sTemp2.length() <= afterPoint) {
						int lenAdd = afterPoint - sTemp2.length();
						for (int i = 0; i<lenAdd; i++) {
							sTemp2 += "0";
						}
					} else {
						String sTemp3 = sTemp2.substring(afterPoint, afterPoint + 1);
						if (afterPoint == 0) {
							if (Integer.parseInt(sTemp3) >= 5) {
								sTemp1 = String.valueOf(Integer.parseInt(sTemp1) + 1);
								result = Float.parseFloat(sTemp1);
								return result;
							}
						} else {
							sTemp2 = sTemp2.substring(0, afterPoint);
							if (StringTool.isEmptyOrNul(sTemp2)) {
								sTemp2 = "0";
							}
							if (Integer.parseInt(sTemp3) >= 5) {
								sTemp2 = String.valueOf(Integer.parseInt(sTemp2) + 1);
							}
						}
					}
					result = Float.parseFloat(sTemp1 + "." + sTemp2);
				}
			}
		} catch (Exception e) {
		}
		return result;
	}
	
	public static void main(String[] args) {
		Logger.writeln(MathTools.round((float)234.55456, 2)+"");
		Logger.writeln((float)234.55456+"");
	}
	
}
