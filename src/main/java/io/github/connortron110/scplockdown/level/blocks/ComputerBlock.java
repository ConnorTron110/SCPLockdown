package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.level.blockentity.ComputerBlockEntity;
import io.github.connortron110.scplockdown.network.SCPNetwork;
import io.github.connortron110.scplockdown.network.client.screens.CBComputerScreen;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class ComputerBlock extends LockdownHorizontalBlock implements EntityBlock {

	private static final VoxelShape[] COMPUTER_SHAPES = Utils.makeHorizontalShapes(Stream.of(
			Block.box(2, 14, 8.5, 14, 15, 9.5),
			Block.box(1, 0, 1, 15, 1, 7),
			Block.box(2, 0, 12, 14, 1, 15),
			Block.box(2.25, 1, 12.25, 13.75, 3, 14.75),
			Block.box(4.5, 5, 10.5, 11.5, 7, 12.5),
			Block.box(4.5, 3, 12.5, 11.5, 7, 14.5),
			Block.box(2, 4, 9.5, 14, 14, 10.5),
			Block.box(1, 3, 8.5, 2, 15, 9.5),
			Block.box(14, 3, 8.5, 15, 15, 9.5),
			Block.box(2, 3, 8.5, 14, 4, 9.5)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	public ComputerBlock(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!level.isClientSide) {
			if (level.getBlockEntity(pos) != null && level.getBlockEntity(pos) instanceof ComputerBlockEntity) {
				ComputerBlockEntity computer = (ComputerBlockEntity) level.getBlockEntity(pos);
				if (computer.isLocked()) {
					player.sendSystemMessage(computer.getText());
				} else {
					if (player instanceof ServerPlayer serverPlayer) {
						SCPNetwork.NETWORK.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new CBComputerScreen(pos));
					}
				}
			}
		}
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (state.getBlock().equals(SCPBlocks.PERSONAL_COMPUTER.get())) return Shapes.block();
		return COMPUTER_SHAPES[state.getValue(FACING).get2DDataValue()];
	}

	@Override
	public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
		if (pState.getBlock().equals(SCPBlocks.COMPUTER.get())) return false;
		if (pState.getBlock().equals(SCPBlocks.PERSONAL_COMPUTER.get()) && pAdjacentBlockState.getBlock().equals(SCPBlocks.COMPUTER.get())) {
			return false;
		} else return super.skipRendering(pState, pAdjacentBlockState, pSide);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return SCPBlockEntities.COMPUTER.get().create(pPos, pState);
	}
}
