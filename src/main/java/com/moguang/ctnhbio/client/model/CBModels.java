package com.moguang.ctnhbio.client.model;

import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

import java.util.HashMap;
import java.util.Map;

public class CBModels {
    public static final Map<String, ? extends GeoModel<GeoAnimatable>> MODELS = Map.of(
            "bioeclectric_forge", new BioelectricForgeModel(),
            "decomposer", new DecomposerModel(),
            "digester", new DigesterModel(),
            "bioreactor", new BioReactorModel(),
            "brain_in_a_vat", new VatModel(),
            "great_flesh", new GreatFleshModel()
    );
}
