package me.gnat008.MobArenaBuyableClasses.util;

import org.yaml.snakeyaml.DumperOptions.FlowStyle;

public enum YAMLFormat {

    EXTENDED(FlowStyle.BLOCK),
    COMPACT(FlowStyle.AUTO);

    private final FlowStyle style;

    YAMLFormat(FlowStyle style) {
        this.style = style;
    }

    public FlowStyle getStyle() {
        return style;
    }
}
