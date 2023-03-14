public class StringConversion {
    public static void main(String[] args) {

        // String to int
        String value1 = "12345";
        int intValue = Integer.parseInt(value1); 
        System.out.println("intValue = " + intValue); 
    
        // String to double
        String value2 = "12.345";
        double doubleValue = Double.parseDouble(value2);
        System.out.println("doubleValue = " + doubleValue); 

        // String to long
        String value3 = "9876543210";
        long longValue = Long.parseLong(value3);
        System.out.println("longValue = " + longValue); 
    
        // String to short
        String value4 = "321";
        short shortValue = Short.parseShort(value4);
        System.out.println("shortValue = " + shortValue); 

        // String to byte
        String value5 = "-28";
        byte byteValue = Byte.parseByte(value5);
        System.out.println("byteValue = " + byteValue);

        // String to float
        String value6 = "-45.237";
        float floatValue = Float.parseFloat(value6);
        System.out.println("floatValue6 = " + floatValue); 

        // String to hex
        String value7 = "3BABE";
        int hexValue = Integer.parseInt(value7, 16); 
        System.out.println("hexValue = " + hexValue); 
    
        // String to binary
        String value8 = "11001";
        int binaryValue = Integer.parseInt(value8, 2);
        System.out.println("binaryValue = " + binaryValue); 

    } 
}
