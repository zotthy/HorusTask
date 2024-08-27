package app;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WallTest {
    private Wall wall;

    @BeforeEach
    void setUp() {
        wall = new Wall(new ArrayList<>());
    }

    @Test
    void testFindBlocksByColor_FindColorRed() {
        Block block1 = mock(Block.class);
        Block block2 = mock(Block.class);
        Block block3 = mock(Block.class);
        Block block4 = mock(Block.class);

        when(block1.getColor()).thenReturn("pink");
        when(block2.getColor()).thenReturn("red");
        when(block3.getColor()).thenReturn("blue");
        when(block4.getColor()).thenReturn("gray");

        List<Block> blocks = Arrays.asList(block1, block2, block3, block4);
        Wall wall = new Wall(blocks);

        Optional<Block> foundBlocks = wall.findBlockByColor("red");
        assertTrue(foundBlocks.isPresent());
    }

    @Test
    void testFindBlocksByColor_FindColorPink() {
        Block block1 = mock(Block.class);
        Block block2 = mock(Block.class);
        Block block3 = mock(Block.class);
        Block block4 = mock(Block.class);

        when(block1.getColor()).thenReturn("pink");
        when(block2.getColor()).thenReturn("red");
        when(block3.getColor()).thenReturn("blue");
        when(block4.getColor()).thenReturn("gray");

        List<Block> blocks = Arrays.asList(block1, block2, block3, block4);
        Wall wall = new Wall(blocks);

        Optional<Block> foundBlocks = wall.findBlockByColor("pink");
        assertTrue(foundBlocks.isPresent());
    }

    @Test
    void testFindBlocksByColor_FindColorGreenInCombo() {
        Block block1 = mock(Block.class);
        Block block2 = mock(Block.class);
        Block block3 = mock(Block.class);
        ComboBlock comboBlock = mock(ComboBlock.class);

        when(block1.getColor()).thenReturn("pink");
        when(block2.getColor()).thenReturn("red");
        when(block3.getColor()).thenReturn("green");
        when(comboBlock.getColor()).thenReturn("combo");

        List<Block> innerBlocks = Arrays.asList(block3);
        when(comboBlock.getBlocks()).thenReturn(innerBlocks);

        List<Block> blocks = Arrays.asList(block1, block2, comboBlock);
        Wall wall = new Wall(blocks);

        Optional<Block> foundBlocks = wall.findBlockByColor("green");

        assertTrue(foundBlocks.isPresent());
        assertEquals(block3, foundBlocks.get());
    }

    @Test
    void testFindBlocksByColor_FindColorPinkInCombo() {
        Block block1 = mock(Block.class);
        Block block2 = mock(Block.class);
        Block block3 = mock(Block.class);
        ComboBlock comboBlock = mock(ComboBlock.class);

        when(block1.getColor()).thenReturn("pink");
        when(block2.getColor()).thenReturn("red");
        when(block3.getColor()).thenReturn("green");
        when(comboBlock.getColor()).thenReturn("combo");

        List<Block> innerBlocks = Arrays.asList(block3);
        when(comboBlock.getBlocks()).thenReturn(innerBlocks);

        List<Block> blocks = Arrays.asList(block1, block2, comboBlock);
        Wall wall = new Wall(blocks);

        Optional<Block> foundBlocks = wall.findBlockByColor("pink");

        assertTrue(foundBlocks.isPresent());
        assertEquals(block1, foundBlocks.get());
    }

    @Test
    void testFindBlocksByMaterials_FindWood() {
        Block block1 = mock(Block.class);
        Block block2 = mock(Block.class);

        when(block1.getMaterial()).thenReturn("wood");
        when(block2.getMaterial()).thenReturn("stone");

        List<Block> blocks = Arrays.asList(block1, block2);
        Wall wall = new Wall(blocks);

        List<Block> foundBlocks = wall.findBlocksByMaterial("wood");

        assertEquals(1, foundBlocks.size());
        assertTrue(foundBlocks.contains(block1));
    }

    @Test
    void testFindBlocksByMaterials_FindPaper() {
        Block block1 = mock(Block.class);
        Block block2 = mock(Block.class);
        Block block3 = mock(Block.class);
        Block block4 = mock(Block.class);

        when(block1.getMaterial()).thenReturn("wood");
        when(block2.getMaterial()).thenReturn("paper");
        when(block3.getMaterial()).thenReturn("glass");
        when(block4.getMaterial()).thenReturn("paper");

        List<Block> blocks = Arrays.asList(block1, block2, block3, block4);
        Wall wall = new Wall(blocks);

        List<Block> foundBlocks = wall.findBlocksByMaterial("paper");

        assertEquals(2, foundBlocks.size());
        assertTrue(foundBlocks.contains(block2));
        assertTrue(foundBlocks.contains(block4));
    }

    @Test
    void testFindBlockByMaterial_AllMatches_ReturnAllMatchingBlocks() {
        Block block1 = mock(Block.class);
        Block block2 = mock(Block.class);
        Block block3 = mock(Block.class);

        when(block1.getMaterial()).thenReturn("glass");
        when(block2.getMaterial()).thenReturn("glass");
        when(block3.getMaterial()).thenReturn("glass");

        List<Block> blocks = Arrays.asList(block1, block2, block3);
        Wall wall = new Wall(blocks);

        List<Block> foundBlocks = wall.findBlocksByMaterial("glass");

        assertEquals(3, foundBlocks.size());
        assertTrue(foundBlocks.contains(block1));
    }

    @Test
    void testFindBlocksByMaterials_EmptyBlockList() {
        List<Block> blocks = new ArrayList<>();
        Wall wall = new Wall(blocks);

        List<Block> foundBlocks = wall.findBlocksByMaterial("paper");

        assertTrue(foundBlocks.isEmpty());
    }

    @Test
    void testFindBlocksByMaterial_FindStone_InComboBlock(){
        Block block1 = mock(Block.class);
        Block block2 = mock(Block.class);
        Block block3 = mock(Block.class);
        ComboBlock comboBlock = mock(ComboBlock.class);

        when(block1.getMaterial()).thenReturn("glass");
        when(block2.getMaterial()).thenReturn("glass");
        when(block3.getMaterial()).thenReturn("glass");
        when(comboBlock.getBlocks()).thenReturn(new ArrayList<>());

        List<Block> blocks = Arrays.asList(block1, block2, block3);
        Wall wall = new Wall(blocks);

        List<Block> foundBlocks = wall.findBlocksByMaterial("glass");

        assertEquals(3, foundBlocks.size());
        assertTrue(foundBlocks.contains(block1));
    }

    @Test
    void testCount_EmptyList() {
        List<Block> blocks = new ArrayList<>();
        int result = wall.count(blocks);
        assertEquals(0, result);
    }

    @Test
    void testCount_SingleSimpleBlock() {
        Block block = mock(Block.class);
        Block block1 = mock(Block.class);

        List<Block> blocks = Arrays.asList(block, block1);
        int result = wall.count(blocks);
        assertEquals(2, result);
    }

    @Test
    void testCount_SingleComboBlock_Empty() {
        ComboBlock comboBlock = mock(ComboBlock.class);
        when(comboBlock.getBlocks()).thenReturn(new ArrayList<>());

        List<Block> blocks = Arrays.asList(comboBlock);
        int result = wall.count(blocks);
        assertEquals(0, result);
    }

    @Test
    void testCount_SingleComboBlock_FindTwo() {
        Block block = mock(Block.class);
        Block block1 = mock(Block.class);

        ComboBlock comboBlock = mock(ComboBlock.class);
        when(comboBlock.getBlocks()).thenReturn(Arrays.asList(block, block1));

        List<Block> blocks = Arrays.asList(comboBlock);
        int result = wall.count(blocks);
        assertEquals(2, result);
    }
}
