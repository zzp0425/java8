package com.zzp.learn.lambda.collections.customization;

/**
 * Desc
 * Created by zzp
 * on 2016/8/16.19:31
 */
public class StringCombiner {

    private String delim;   //分隔符
    private String prefix;  //前缀
    private String suffix;  //后缀
    public StringBuilder builder;

    public StringCombiner(String delim, String prefix, String suffix) {
        this.delim = delim;
        this.prefix = prefix;
        this.suffix = suffix;
        this.builder = new StringBuilder();
    }

    public StringCombiner add(String element) {
        if (areAtStart()) {
            this.builder.append(prefix);
        } else {
            this.builder.append(delim);
        }
        this.builder.append(element);
        return this;
    }

    public StringCombiner merge(StringCombiner other) {
        if (other.builder.length() > 0) {
            if (areAtStart()) {
                this.builder.append(prefix);
            } else {
                this.builder.append(delim);
            }
            this.builder.append(other.builder, prefix.length(), other.builder.length());
        }
        return this;
    }

    private boolean areAtStart() {
        return this.builder.length() == 0;
    }

    @Override
    public String toString() {
        if (areAtStart()) {
            builder.append(prefix);
        }
        builder.append(suffix);
        return builder.toString();
    }
}
