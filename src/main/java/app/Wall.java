package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Wall implements Structure {

    private final List<Block> blockList;

    public Wall(List<Block> blockList) {
        this.blockList = blockList;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findBlocksByColor(color,blockList);
    }

    private Optional<Block> findBlocksByColor(String color, List<Block> blocks) {
        for (Block block : blocks) {
            if (Objects.equals(block.getColor(), color)) {
                return Optional.of(block);
            }
            if (block instanceof CompositeBlock) {
                Optional<Block> found = findBlocksByColor(color, ((CompositeBlock) block).getBlocks());
                if (found.isPresent()) {
                    return found;
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return findBlocksByMaterial(material, blockList);
    }

    private List<Block> findBlocksByMaterial(String material, List<Block> blockList) {
        List<Block> foundBlocks = new ArrayList<>();
        for (Block block : blockList) {
            if (Objects.equals(block.getMaterial(), material)) {
                foundBlocks.add(block);
            }
            if (block instanceof CompositeBlock) {
                foundBlocks.addAll(findBlocksByMaterial(material, ((CompositeBlock) block).getBlocks()));
            }
        }
        return foundBlocks;
    }

    @Override
    public int count() {
        return count(blockList);
    }

    public int count(List<Block> blocks) {
        int sum = 0;
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                sum += count(((CompositeBlock) block).getBlocks());
            }
            else {
                sum++;
            }
        }
        return sum;
    }
}
