package com.alexfu.sqlitequerybuilder.real;

import com.alexfu.sqlitequerybuilder.api.SQLiteQueryBuilder;
import org.junit.Test;

import java.sql.SQLException;

import static com.alexfu.sqlitequerybuilder.real.ConnectionUtils.tables;
import static org.assertj.core.api.Assertions.assertThat;

public final class DropTableTest extends SQLiteTest {

  @Override
  public void before() throws SQLException {
    super.before();

    // Seed database
    String sql = "CREATE TABLE myTable (id INTEGER)";
    statement.execute(sql);
  }

  @Test
  public final void testDropTable() throws SQLException {
    // Arrange
    String sql = SQLiteQueryBuilder
      .drop()
      .table("myTable")
      .ifExists()
      .toString();

    // Act
    statement.execute(sql);

    // Assert
    assertThat(tables(connection)).doesNotContain("myTable");
  }

  @Test
  public final void testDropTableNoIfExists() throws SQLException {
    // Arrange
    String sql = SQLiteQueryBuilder
      .drop()
      .table("myTable")
      .toString();

    // Act
    statement.execute(sql);

    // Assert
    assertThat(tables(connection)).doesNotContain("myTable");
  }
}
