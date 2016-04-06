package com.magooup.learn;

/**
 * Created by zhiyong.ma on 2016/3/31.
 */
class TypeOperator extends TypeExp {
    private String operator;
    private TypeList types;

    public TypeOperator(String operator, TypeList types) {
        this.operator = operator;
        this.types = types;
    }

    public static TypeExp newTypeOperator(String operator, TypeList types) {
        return new TypeOperator(operator, types);
    }
}

abstract class TypeExp {

}

class TypeList {
}