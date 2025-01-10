package com.ownwn.mixin.client;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
	@Redirect(method = "shouldStopSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isGliding()Z"))
	private boolean redirectIsGliding(ClientPlayerEntity instance) {
		return false;
	}

	@Redirect(method = "shouldStopSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"))
	private boolean redirectIsUsingItem(ClientPlayerEntity instance) {
		return false;
	}

	@Redirect(method = "shouldStopSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isSubmergedInWater()Z"))
	private boolean redirectIsSubmergedInWater(ClientPlayerEntity instance) {
		return false;
	}

	@Redirect(method = "shouldStopSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;shouldSlowDown()Z"))
	private boolean redirectShouldSlowDown(ClientPlayerEntity instance) {
		return false;
	}
}