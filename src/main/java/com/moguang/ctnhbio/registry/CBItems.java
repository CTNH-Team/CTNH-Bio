package com.moguang.ctnhbio.registry;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBItems {
    public static void init() {
        CBMaterialItems.generateTools();
    }
//    public static final ItemEntry<Item> BIO_CAPACITOR = REGISTRATE.item("bio_capacitor", Item::new)
//            .lang("Bio Capacitor")
//            .register();
//    public static final ItemEntry<Item> BIO_TRANSISTOR = REGISTRATE.item("bio_transistor", Item::new)
//            .lang("Bio Transistor")
//            .register();
//    public static final ItemEntry<Item> BIO_RESISTOR = REGISTRATE.item("bio_resistor", Item::new)
//            .lang("Bio Resistor")
//            .register();
//    public static final ItemEntry<Item> BIO_INDUCTOR = REGISTRATE.item("bio_inductor", Item::new)
//            .lang("Bio Inductor")
//            .register();
//    public static final ItemEntry<Item> BIO_DIODE = REGISTRATE.item("bio_diode", Item::new)
//            .lang("Bio Diode")
//            .register();
//    public static final ItemEntry<Item> WETWARE_CIRCUIT_BOARD = REGISTRATE.item("wetware_circuit_board", Item::new)
//            .lang("Wet Circuit Board")
//            .register();
//    public static final ItemEntry<Item> WETWARE_PRINTED_CIRCUIT_BOARD = REGISTRATE.item("wetware_printed_circuit_board", Item::new)
//            .lang("Wet Printed Circuit Board")
//            .register();
}
