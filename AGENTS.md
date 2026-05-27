# CTNH-Bio KNOWLEDGE BASE

## OVERVIEW
CTNH-Bio adds Biomancy/living-machine systems, entity/recipe capabilities, biological machines, and generated recipes/resources under mod id `ctnhbio`.

## WHERE TO LOOK
- Mod entry: `src/main/java/com/moguang/ctnhbio/CTNHBio.java`. Forge mod initialization.
- GT addon: `src/main/java/com/moguang/ctnhbio/CTNHBioGTAddon.java`. GTCEu material/recipe/machine registration.
- Config: `src/main/java/com/moguang/ctnhbio/CBConfig.java`. Module config.
- API/capabilities: `src/main/java/com/moguang/ctnhbio/api/`. Recipe capabilities, tool types, GUI textures, machine APIs.
- Registries: `src/main/java/com/moguang/ctnhbio/registry/`. Blocks, items, multiblocks, recipes.
- Datagen: `src/main/java/com/moguang/ctnhbio/data/`. Source for generated resources.
- JEI/client/rendering: `src/main/java/com/moguang/ctnhbio/integration/`, `client/`. UI categories and renderers.
- Mixins: `src/main/java/com/moguang/ctnhbio/mixin/`, `src/main/resources/ctnhbio.mixins.json`. Keep mixin classes and JSON aligned.

## REGISTRATION ENTRYPOINTS
- Registrate/root: `registry/CBRegistrate.java`; mod/addon entrypoints are `CTNHBio.java` and `CTNHBioGTAddon.java`.
- Items/blocks/entities: `registry/CBItems.java`, `registry/CBBlocks.java`, `registry/CBEntities.java`, `registry/CBMaterialItems.java`.
- Machines/multiblocks: `registry/CBMachines.java`, `registry/CBMultiblocks.java`.
- Materials/effects/serums/sounds/tags: `registry/CBMaterials.java`, `CBMobEffects.java`, `CBSerums.java`, `CBSoundEntries.java`, `CBTags.java`.
- Recipe infrastructure: `registry/CBRecipeTypes.java`, `CBRecipes.java`, `CBRecipeCapabilities.java`, `CBRecipeConditions.java`, `utils/CBRecipeModifiers.java`.
- Recipe generation: `data/recipe/` includes living, multi, vanilla, removal, and builder classes; keep Bio-specific living-machine recipes here, cross-module recipes usually go to Core.
- Datagen/lang: `data/CBDatagen.java`, `data/lang/`.

## CONVENTIONS
- Namespace is `com.moguang.ctnhbio`; registry prefixes generally use `CB`.
- `src/main/resources/data/ctnhbio/recipes/decomposing` has many hand-authored/static recipe JSON files.
- `src/generated/resources` is produced by `:modules:CTNH-Bio:runData`.

## COMMANDS
```bash
./gradlew :modules:CTNH-Bio:build
./gradlew :modules:CTNH-Bio:runData
./gradlew :modules:CTNH-Bio:spotlessCheck
```

## ANTI-PATTERNS
- Do not collapse biological recipe capabilities into Core; this module owns its living-machine abstractions.
- Do not assume all recipe JSON is generated; check whether it is under `src/main/resources` or `src/generated/resources`.
