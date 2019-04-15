package casebook.domain.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {

    private Sector sector;
    private String profession;
    private String salary;
    private String description;

    public Job() {
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "sector", nullable = false)
    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    @Column(name = "profession", nullable = false)
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Column(name = "salary", nullable = false)
    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
