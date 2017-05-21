package cn.aezo.hibernate.one2one_uni_fk_composite;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(Wife2PK.class)
public class Wife2 {
    private int id;
    private String name;

    @Id
    public int getId() {
        return id;
    }

    @Id
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
