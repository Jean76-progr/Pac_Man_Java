package ijae.xjanelj.model.field;

public class Field {
    private FieldType type;
    private int row;
    private int col;

    public Field(FieldType type, int row, int col) {
        this.type = type;
        this.row = row;
        this.col = col;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }
}