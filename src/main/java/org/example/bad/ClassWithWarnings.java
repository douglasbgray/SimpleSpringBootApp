package org.example.bad;

// Unused import
import java.util.List;
import java.util.Objects;

/**
 * Class with some intentional pmd warnings to test reporting.
 */
public class ClassWithWarnings {

  private String name;

  public ClassWithWarnings(String name) {
    this.name = name;
  }

  // No hashcode method
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ClassWithWarnings that = (ClassWithWarnings) o;
    return Objects.equals(name, that.name);
  }

}
