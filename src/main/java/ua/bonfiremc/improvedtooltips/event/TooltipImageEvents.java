package ua.bonfiremc.improvedtooltips.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class TooltipImageEvents {
    public static final Map<DataComponentType<?>, Event<? extends TooltipImageCallback<?>>> COMPONENT_TO_CALLBACK = new HashMap<>();

    private TooltipImageEvents() {
    }

    @SuppressWarnings("unchecked")
    public static <T> Event<TooltipImageCallback<T>> getOrCreate(DataComponentType<T> componentType) {
        return (Event<TooltipImageCallback<T>>) COMPONENT_TO_CALLBACK.computeIfAbsent(componentType, _ -> EventFactory.<TooltipImageCallback<T>>createArrayBacked(TooltipImageCallback.class,
            (listeners) -> (stack, component) -> {
                for (TooltipImageCallback<T> listener : listeners) {
                    TooltipComponent image = listener.getImage(stack, component);

                    if (image != null) {
                        return image;
                    }
                }

                return null;
            }
        ));
    }

    @SuppressWarnings("unchecked")
    public static <T> @Nullable Event<TooltipImageCallback<T>> get(DataComponentType<T> componentType) {
        return (Event<TooltipImageCallback<T>>) COMPONENT_TO_CALLBACK.get(componentType);
    }
}
