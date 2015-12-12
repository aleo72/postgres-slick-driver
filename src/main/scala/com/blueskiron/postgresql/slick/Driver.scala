package com.blueskiron.postgresql.slick

import com.github.tminglei.slickpg.ExPostgresDriver
import com.github.tminglei.slickpg.ExPostgresDriver
import com.github.tminglei.slickpg.PgArraySupport
import com.github.tminglei.slickpg.PgSearchSupport
import com.github.tminglei.slickpg.PgNetSupport
import com.github.tminglei.slickpg.PgPlayJsonSupport
import com.github.tminglei.slickpg.PgLTreeSupport
import com.github.tminglei.slickpg.PgRangeSupport
import com.github.tminglei.slickpg.PgDateSupport
import com.github.tminglei.slickpg.PgHStoreSupport
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import com.github.tminglei.slickpg.utils.SimpleArrayUtils

/**
 * {@link https://github.com/tminglei/slick-pg } Custom Slick PostgreSql Driver
 * 
 * @author juri
 *
 */
trait Driver extends ExPostgresDriver
    with PgArraySupport
    with PgDateSupport
    with PgRangeSupport
    with PgHStoreSupport
    with PgPlayJsonSupport
    with PgSearchSupport
    with PgNetSupport
    with PgLTreeSupport {

  def pgjson = "jsonb" 
  
  override val api = ThisAPI

  object ThisAPI extends API 
      with ArrayImplicits
      with DateTimeImplicits
      with JsonImplicits
      with NetImplicits
      with LTreeImplicits
      with RangeImplicits
      with HStoreImplicits
      with SearchImplicits
      with SearchAssistants {

    implicit val strListTypeMapper = new SimpleArrayJdbcType[String]("text").to(_.toVector)
    implicit val playJsonArrayTypeMapper =
      new AdvancedArrayJdbcType[JsValue](pgjson,
        (s) => SimpleArrayUtils.fromString[JsValue](Json.parse(_))(s).orNull,
        (v) => SimpleArrayUtils.mkString[JsValue](_.toString())(v)
      ).to(_.toList)

  }
}

object Driver extends Driver