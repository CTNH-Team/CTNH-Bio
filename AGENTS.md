# CTNH-Bio KNOWLEDGE BASE

## OVERVIEW
CTNH-Bio adds Biomancy/living-machine systems, entity/recipe capabilities, biological machines, and generated recipes/resources under mod id `ctnhbio`.

## WHERE TO LOOK
- Mod entry: `src/main/java/com/moguang/ctnhbio/CTNHBio.java`. Forge mod initialization.
- GT addon: `src/main/java/com/moguang/ctnhbio/CTNHBioGTAddon.java`. GTCEu material/recipe/machine registration.
- Config: `src/main/java/com/moguang/ctnhbio/CBConfig.java`. Module config.
- API/capabilities: `src/main/java/com/moguang/ctnhbio/api/`. Recipe capabilities, tool types, GUI textures, machine APIs.
- Event/proxy wiring: `src/main/java/com/moguang/ctnhbio/common/CommonProxy.java`, `event/EventHandler.java`. GT registry listeners, Jade status provider, Forge capabilities, datagen, and material hooks.
- Registries: `src/main/java/com/moguang/ctnhbio/registry/`. Blocks, items, multiblocks, recipes.
- Living systems: `api/block*`, `api/entity`, `api/machine`, `machine/braininavat/`, `machine/bioobservation/`, `machine/multiblock/`. Living meta machines, Brain in a Vat, Hostile Observer, Cogni assembler.
- Recipe ingredients: `api/recipe/ingredient/`, `api/recipe/matcher/`, `api/recipe/content/`. Entity/model ingredients, property operators, and nutrient serialization.
- Datagen: `src/main/java/com/moguang/ctnhbio/data/`. Source for generated resources.
- XEI/Jade/client/rendering: `src/main/java/com/moguang/ctnhbio/integration/`, `client/`. Living-machine Jade status, UI categories, and renderers.
- Mixins: `src/main/java/com/moguang/ctnhbio/mixin/`, `src/main/resources/ctnhbio.mixins.json`. Keep mixin classes and JSON aligned.

## REGISTRATION ENTRYPOINTS
- Registrate/root: `registry/CBRegistrate.java`; mod/addon entrypoints are `CTNHBio.java` and `CTNHBioGTAddon.java`.
- GT addon hooks: `CTNHBioGTAddon.initializeAddon()` initializes items/blocks and registers `ModelIngredient` map ingredients; `registerRecipeCapabilities()` initializes `CBRecipeCapabilities`; `registerRecipeKeys()` exposes nutrient KubeJS keys `NU_IN` / `NU_OUT`.
- Common proxy: `common/CommonProxy.java` initializes entities, creative tabs, datagen, registrate, `PropertyOperators`, `EntityProperties`, and Jade `LivingMachineStatusProvider`.
- Forge capabilities: `CommonProxy.registerCapabilities()` delegates to `api/capability/forge/CBCapabilities.java`.
- Items/blocks/entities: `registry/CBItems.java`, `registry/CBBlocks.java`, `registry/CBEntities.java`, `registry/CBMaterialItems.java`.
- Machines/multiblocks: `registry/CBMachines.java`, `registry/CBMultiblocks.java`.
- Materials/effects/serums/sounds/tags: `registry/CBMaterials.java`, `CBMobEffects.java`, `CBSerums.java`, `CBSoundEntries.java`, `CBTags.java`.
- Recipe infrastructure: `registry/CBRecipeTypes.java`, `CBRecipes.java`, `CBRecipeCapabilities.java`, `CBRecipeConditions.java`, `utils/CBRecipeModifiers.java`.
- Recipe generation: `data/recipe/` includes living, multi, Cogni, Hostile Observation, vanilla, removal, and builder classes; keep Bio-specific living-machine recipes here, cross-module recipes usually go to Core.
- Datagen/lang: `data/CBDatagen.java`, `data/lang/`.

## CONVENTIONS
- Namespace is `com.moguang.ctnhbio`; registry prefixes generally use `CB`.
- `src/main/resources/data/ctnhbio/recipes/decomposing` has many hand-authored/static recipe JSON files.
- `src/generated/resources` is produced by `:modules:CTNH-Bio:runData`.
- Mixins span Biomancy, Hostile Neural Networks, EMI/ALI, Create, and GTCEu recipe/machine internals; treat them as compatibility patches, not generic helpers.

## COMMANDS
```bash
./gradlew :modules:CTNH-Bio:build
./gradlew :modules:CTNH-Bio:runData
./gradlew :modules:CTNH-Bio:spotlessCheck
```

## ANTI-PATTERNS
- Do not collapse biological recipe capabilities into Core; this module owns its living-machine abstractions.
- Do not assume all recipe JSON is generated; check whether it is under `src/main/resources` or `src/generated/resources`.
- Do not bypass `PropertyOperators` / `EntityProperties` when adding entity-model recipe matching; those registries are initialized explicitly in `CommonProxy.init()`.
