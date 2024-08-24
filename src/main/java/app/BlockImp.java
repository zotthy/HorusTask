package app;

public class BlockImp implements Block{
    private final String color;
    private final String material;

    public BlockImp(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return "BlockImp{" +
                "color='" + color + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}
