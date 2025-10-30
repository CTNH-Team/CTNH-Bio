package com.moguang.ctnhbio.machine.multiblock.part;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.ConfiguratorPanel;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyConfigurator;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyConfiguratorButton;
import com.gregtechceu.gtceu.api.gui.widget.BlockableSlotWidget;
import com.gregtechceu.gtceu.api.gui.widget.IntInputWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IMachineLife;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IWorkableMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList;
import com.gregtechceu.gtceu.api.recipe.ActionResult;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.texture.ItemStackTexture;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.annotation.RequireRerender;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.lowdragmc.lowdraglib.utils.Position;
import com.moguang.ctnhbio.api.machine.trait.NeuralModelContainer;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import com.moguang.ctnhbio.utils.MetaMachineUtils;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jetbrains.annotations.NotNull;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Suffix;

import java.util.ArrayList;
import java.util.List;

import static dev.shadowsoffire.hostilenetworks.Hostile.Items.PREDICTION_MATRIX;

@Suffix("neuralmodel_accessor_machine")
public class NeuralModelAccessorMachine extends MultiblockPartMachine implements IMachineLife{

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NeuralModelAccessorMachine.class,
            MultiblockPartMachine.MANAGED_FIELD_HOLDER);

    @Getter
    @Setter
    @Persisted
    @DescSynced
    protected boolean outputModel = false;

    @Getter
    @Setter
    @Persisted
    @DescSynced
    protected int ticksPerCycle = 200;

    protected int tick;

    @Persisted
    boolean isAdvanced;

    @Override @NotNull
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Persisted
    @DescSynced
    @Getter
    private final NeuralModelContainer modelHolder;

    @MustBeInvokedByOverriders
    @Override
    public void removedFromController(@NotNull IMultiController controller) {
        super.removedFromController(controller);
        if (controllers.isEmpty())
            setLocked(false);
    }

    @Override
    public boolean afterWorking(IWorkableMultiController controller) {
        setLocked(false);
        return super.afterWorking(controller);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if(isAdvanced) subscribeServerTick(this::tryOutputModel);
    }

    public NeuralModelAccessorMachine(IMachineBlockEntity holder, boolean isAdvanced) {
        super(holder);
        modelHolder = new NeuralModelContainer(this);
        this.isAdvanced = isAdvanced;
    }

    //Life cycle
    @Override
    public void onMachineRemoved() {
        clearInventory(this.modelHolder.storage);
    }

    //Recipe Related
    public boolean isLocked() {
        return modelHolder.isLocked();
    }
    public void setLocked(boolean locked){
        modelHolder.setLocked(locked);
    }

    @Override
    public ModularUI createUI(Player entityPlayer) {
        return super.createUI(entityPlayer);
    }

    //UI
    @Override
    public Widget createUIWidget() {
        return new WidgetGroup(new Position(0, 0))
                .addWidget(new BlockableSlotWidget(modelHolder, 0, 50/2-18-6, 50/2-18)
                        .setIsBlocked(this::isLocked)
                        .setBackground(GuiTextures.SLOT, GuiTextures.RESEARCH_STATION_OVERLAY));
    }

    @Override
    public @NotNull List<RecipeHandlerList> getRecipeHandlers() {
        return MetaMachineUtils.getRecipeHandlers(this, modelHolder);
    }

    @Override
    public boolean canShared() {
        return false;
    }

    private void tryOutputModel(){
        var machine = getControllers().stream().findFirst().orElse(null);
        if(machine instanceof WorkableMultiblockMachine r && r.isFormed())
        {
            if(r.isActive()) tick=0;
            else if(isOutputModel() && !modelHolder.getItemStack().isEmpty()) tick++;
            if(tick >= ticksPerCycle)
            {
                var model = modelHolder.getItemStack();
                var Recipe = GTRecipeBuilder.ofRaw().outputItems(model).buildRawRecipe();
                if (RecipeHelper.matchRecipe(r, Recipe).isSuccess() &&
                        RecipeHelper.handleRecipeIO(r, Recipe, IO.OUT, r.getRecipeLogic().getChanceCaches()) == ActionResult.SUCCESS) {
                    tick = 0;
                    modelHolder.setStackInSlot(0, ItemStack.EMPTY);
                }
            }
        }
    }

    @CN({
            "模型输出功能已启用",
            "模型输出功能已禁用"
    })
    @EN({
            "Model output is enabled",
            "Model output is disabled"
    })
    static Lang[] output_model;

    @Override
    public void attachConfigurators(ConfiguratorPanel configuratorPanel) {
        if(!isAdvanced) return;
        configuratorPanel.attachConfigurators(new IFancyConfiguratorButton.Toggle(
                GuiTextures.BUTTON_POWER.getSubTexture(0, 0, 1, 0.5),
                GuiTextures.BUTTON_POWER.getSubTexture(0, 0.5, 1, 0.5),
                this::isOutputModel, (clickData, pressed) -> this.setOutputModel(pressed))
                .setTooltipsSupplier(pressed -> List.of(
                        pressed ? output_model[0].translate(): output_model[1].translate()
                )),
                new AutoOutputModelConfigurator(this)
        );
    }

    @Suffix("auto_output_model_configurator")
    public static class AutoOutputModelConfigurator implements IFancyConfigurator {

        private NeuralModelAccessorMachine machine;

        public AutoOutputModelConfigurator(NeuralModelAccessorMachine machine){
            this.machine = machine;
        }

        @CN("停止工作多少tick后使机器输出模型")
        @EN("How many ticks from stop working to output ths model")
        static Lang ticks;

        @Override
        public Component getTitle() {
            return ticks.translate();
        }

        @Override
        public IGuiTexture getIcon() {
            return new ItemStackTexture(PREDICTION_MATRIX.get());
        }


        @Override
        public Widget createConfigurator() {
            var group = new WidgetGroup(0, 0, 90, 26);
            group.addWidget(new IntInputWidget(4, 2, 81, 14, machine::getTicksPerCycle,
                    machine::setTicksPerCycle).setMin(1));
            return group;
        }
    }
}
