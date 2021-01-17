package ldbc.snb.datagen.spark.transformation.transform

import ldbc.snb.datagen.spark.sql._
import ldbc.snb.datagen.spark.transformation.model.Cardinality.NN
import ldbc.snb.datagen.spark.transformation.model.EntityType
import ldbc.snb.datagen.spark.transformation.model.EntityType.Edge
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

private object Interactive {

  def columns(tpe: EntityType, cols: Seq[String]) = tpe match {
    case tpe if tpe.isStatic => cols
    case Edge("Knows", "Person", "Person", NN, false) =>
      val rawCols = Set("deletionDate", "explicitlyDeleted", "weight")
      cols.filter(rawCols.contains)
    case _ =>
      val rawCols = Set("deletionDate", "explicitlyDeleted")
      cols.filter(rawCols.contains)
  }


  def snapshotPart(tpe: EntityType, df: DataFrame, bulkLoadThreshold: Long, simulationEnd: Long) = {
    val filterBulkLoad = (ds: DataFrame) => ds
      .filter(
        ds("creationDate") < bulkLoadThreshold &&
        ds("deletionDate") >= bulkLoadThreshold &&
        ds("deletionDate") <= simulationEnd
      )

    tpe match {
      case tpe if tpe.isStatic => df
      case tpe => filterBulkLoad(df).select(columns(tpe, df.columns).map(name => col(qualified(name))): _*)
    }
  }

}
