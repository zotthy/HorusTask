package app;

import java.util.List;

public class ComboBlock implements CompositeBlock{
    private List<Block> blocks;

    public ComboBlock(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public String getColor() {
        return null;
    }

    @Override
    public String getMaterial() {
        return null;
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }
}
