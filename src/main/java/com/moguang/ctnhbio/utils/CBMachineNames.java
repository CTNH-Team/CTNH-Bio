package com.moguang.ctnhbio.utils;

import java.util.HashMap;
import java.util.Map;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class CBMachineNames {
    public static Map<String, String> CNName = new HashMap<>();
    public static Map<String, String> ENName = new HashMap<>();
    public static final Map<Integer, String> LEVEL_CN = new HashMap<>() {{
        put(LV, "§f细胞%s§r");
        put(MV, "§b组织%s§r");
        put(HV, "§6器官%s§r");
        put(EV, "§5系统%s§r");
        put(IV, "§9个体%s§r");
    }};

    public static final Map<Integer, String> LEVEL_EN = new HashMap<>() {{
        put(LV, "§fCellular %s§r");
        put(MV, "§bTissue %s§r");
        put(HV, "§6Organ %s§r");
        put(EV, "§5System %s§r");
        put(IV, "§9Organism %s§r");
    }};


    static {
        CNName.putAll(Map.of(
                "bioelectric_forge", "生物电炉",
                "decomposer", "分解者",
                "digester","消化器",
                "bioreactor","反应腔"
        ));
        ENName.putAll(Map.of(
                "bioelectric_forge", "Bioelectric Forge",
                "decomposer", "Decomposer",
                "digester","Digester",
                "bioreactor","Reactor"
        ));
    }

    public static String getCNName(String machineId, int tier) {
        String base = CNName.get(machineId);
        String format = LEVEL_CN.getOrDefault(tier, "%s");
        return String.format(format, base);
    }

    public static String getENName(String machineId, int tier) {
        String base = ENName.get(machineId);
        String format = LEVEL_EN.getOrDefault(tier, "%s");
        return String.format(format, base);
    }
}
