package lexal.btb.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLog;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;

import java.util.Objects;
import java.util.Random;

public class BlockBirchLog extends BlockLog {
    private final boolean isSyrup;
    public BlockBirchLog(String key, int id,boolean isSyrup) {
        super(key, id);
        this.isSyrup = isSyrup;
        setTicking(true);
    }
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        if (Objects.requireNonNull(dropCause) == EnumDropCause.SILK_TOUCH) {
            return new ItemStack[]{new ItemStack(this)};
        }
        return new ItemStack[]{new ItemStack(Block.logBirch)};
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!isSyrup){
            if (world.seasonManager.getCurrentSeason() != null && world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_FALL) {
                if (rand.nextInt(32) == 0){
                    world.setBlockAndMetadataWithNotify(x,y,z, BTBBlocks.birchSyrupLog.id, world.getBlockMetadata(x,y,z));
                }
            }
        }
    }
}
