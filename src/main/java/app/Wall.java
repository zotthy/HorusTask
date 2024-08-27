package app;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wall implements Structure {

    private final List<Block> blockList;

    public Wall(List<Block> blockList) {
        this.blockList = blockList;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findBlocksByColor(color, blockList);
    }

    private Optional<Block> findBlocksByColor(String color, List<Block> blocks) {
        return blocks.stream().flatMap(block -> {
                    Stream<Block> blockStream = Objects.equals(block.getColor(), color) ? Stream.of(block) : Stream.empty();
                    Stream<Block> blockInComposite = block instanceof CompositeBlock
                            ? findBlocksByColor(color, ((CompositeBlock) block).getBlocks()).stream()
                            : Stream.empty();
                    return Stream.concat(blockStream,blockInComposite);
                })
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return findBlocksByMaterial(material, blockList);
    }

    private List<Block> findBlocksByMaterial(String material, List<Block> blockList) {
        return blockList.stream().flatMap(block -> {
                    Stream<Block> blockStream = Objects.equals(block.getMaterial(), material) ? Stream.of(block) : Stream.empty();
                    Stream<Block> blockInComposite = block instanceof CompositeBlock
                            ? findBlocksByMaterial(material, ((CompositeBlock) block).getBlocks()).stream()
                            : Stream.empty();

                    return Stream.concat(blockStream, blockInComposite);
                })
                .collect(Collectors.toList());
    }


    @Override
    public int count() {
        return count(blockList);
    }

    public int count(List<Block> blocks) {
        return blocks.stream()
                .mapToInt(block -> block instanceof CompositeBlock
                        ? count(((CompositeBlock) block).getBlocks())
                        : 1)
                .sum();
    }

}
