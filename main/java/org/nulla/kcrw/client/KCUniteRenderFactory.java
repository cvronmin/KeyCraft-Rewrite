package org.nulla.kcrw.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.google.common.collect.ObjectArrays;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@SuppressWarnings("rawtypes")
public class KCUniteRenderFactory<T extends Entity> implements IRenderFactory<T> {
	private final Class<? extends Render> renderclass;
	private final Object[] ctorArgs;
	private boolean needRI = false;
	public KCUniteRenderFactory(Class<? extends Render> render, Object[] ctor) {
		renderclass = render;
		ctorArgs = ctor;
	}
	public KCUniteRenderFactory(Class<? extends Render> render, Object[] ctor, boolean needItemRender) {
		this(render, ctor);
		needRI = needItemRender;
	}
	@Override
	public Render<T> createRenderFor(RenderManager manager) {
		try {
			Class<?>[] ctorArgClasses = needRI ? new Class<?>[ctorArgs.length + 2] : new Class<?>[ctorArgs.length + 1];
			ctorArgClasses[0] = RenderManager.class;
            for (int idx = 1; idx < ctorArgClasses.length - (needRI ? 1 : 0); idx++)
            {
            	if (ctorArgs[idx - 1] instanceof Item) {
					ctorArgClasses[idx] = Item.class;
				}
            	else{
                    ctorArgClasses[idx] = ctorArgs[idx - 1].getClass();
            	}
            }
            if (needRI) {
				ctorArgClasses[ctorArgClasses.length - 1] = RenderItem.class;
			}
			Constructor<? extends Render> itemCtor = renderclass.getConstructor(ctorArgClasses);
			return itemCtor.newInstance(needRI ? ObjectArrays.concat(ObjectArrays.concat(manager, ctorArgs), KCClientUtils.getMC().getRenderItem()) : ObjectArrays.concat(manager, ctorArgs));
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
