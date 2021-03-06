/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.yahoo.dataplatform.mds.blackbox;

import java.io.IOException;

import java.util.Set;
import java.util.HashSet;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import jp.co.yahoo.dataplatform.config.Configuration;

import jp.co.yahoo.dataplatform.schema.objects.*;

import jp.co.yahoo.dataplatform.mds.spread.expression.*;
import jp.co.yahoo.dataplatform.mds.spread.column.filter.*;
import jp.co.yahoo.dataplatform.mds.spread.column.*;
import jp.co.yahoo.dataplatform.mds.binary.*;
import jp.co.yahoo.dataplatform.mds.binary.maker.*;

public class TestConstNumberCellIndex{

  @DataProvider(name = "target_class")
  public Object[][] data1() throws IOException{
    return new Object[][] {
      { createConstTestData( new ByteObj( (byte)15 ) ) },
      { createConstTestData( new ShortObj( (short)15 ) ) },
      { createConstTestData( new IntegerObj( 15 ) ) },
      { createConstTestData( new LongObj( 15 ) ) },
      { createConstTestData( new FloatObj( 15.0f ) ) },
      { createConstTestData( new DoubleObj( 15.0d ) ) },

      { createConstTestData( new StringObj( "15" ) ) },
      { createConstTestData( new BytesObj( "15".getBytes() ) ) },
    };
  }

  private IColumn createConstTestData( final PrimitiveObject value ) throws IOException{
    ConstantColumnBinaryMaker maker = new ConstantColumnBinaryMaker();
    ColumnBinary columnBinary = ConstantColumnBinaryMaker.createColumnBinary( value , "t" , 10 );
    return maker.toColumn( columnBinary );
  }

  public void dumpFilterResult( final boolean[] result ){
    System.out.println( "-----------------------" );
    System.out.println( "Integer cell index test result." );
    System.out.println( "-----------------------" );
    for( int i = 0 ; i < result.length ; i++ ){
      System.out.println( String.format( "index:%d = %s" , i , Boolean.toString( result[i] ) ) );
    }
  }

  @Test( dataProvider = "target_class" )
  public void T_equal_obj_1( final IColumn column ) throws IOException{
    int[] mustReadIndex = { 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 };
    PrimitiveObject[] compareData = new PrimitiveObject[]{ new ByteObj( (byte)15 ) , new ShortObj( (short)15 ) , new IntegerObj( 15 ) , new LongObj( 15 ) , new FloatObj( 15.0f ) , new DoubleObj( 15.0d ) };
    for( PrimitiveObject obj : compareData ){
      IFilter filter = new NumberFilter( NumberFilterType.EQUAL , obj );
      boolean[] filterResult = new boolean[10];
      filterResult = column.filter( filter , filterResult );
      if( filterResult == null ){
        assertTrue( true );
        return;
      }
      for( int i = 0 ; i < mustReadIndex.length ; i++ ){
        assertTrue( filterResult[mustReadIndex[i]] );
      }
    }
  }

  @Test( dataProvider = "target_class" )
  public void T_notequal_obj_1( final IColumn column ) throws IOException{
    int[] mustReadIndex = { 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 };
    PrimitiveObject[] compareData = new PrimitiveObject[]{ new ByteObj( (byte)-10 ) , new ShortObj( (short)-10 ) , new IntegerObj( -10 ) , new LongObj( -10 ) , new FloatObj( -10.0f ) , new DoubleObj( -10.0d ) };
    for( PrimitiveObject obj : compareData ){
      IFilter filter = new NumberFilter( NumberFilterType.NOT_EQUAL , obj );
      boolean[] filterResult = new boolean[10];
      filterResult = column.filter( filter , filterResult );
      if( filterResult == null ){
        assertTrue( true );
        return;
      }
      for( int i = 0 ; i < mustReadIndex.length ; i++ ){
        assertTrue( filterResult[mustReadIndex[i]] );
      }
    }
  }

  @Test( dataProvider = "target_class" )
  public void T_lt_obj_1( final IColumn column ) throws IOException{
    int[] mustReadIndex = { 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 };
    PrimitiveObject[] compareData = new PrimitiveObject[]{ new ByteObj( (byte)16 ) , new ShortObj( (short)16 ) , new IntegerObj( 16 ) , new LongObj( 16 ) , new FloatObj( 16.0f ) , new DoubleObj( 16.0d ) };
    for( PrimitiveObject obj : compareData ){
      IFilter filter = new NumberFilter( NumberFilterType.LT , obj );
      boolean[] filterResult = new boolean[10];
      filterResult = column.filter( filter , filterResult );
      if( filterResult == null ){
        assertTrue( true );
        return;
      }
      for( int i = 0 ; i < mustReadIndex.length ; i++ ){
        assertTrue( filterResult[mustReadIndex[i]] );
      }
    }
  }

  @Test( dataProvider = "target_class" )
  public void T_le_obj_1( final IColumn column ) throws IOException{
    int[] mustReadIndex = { 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 };
    PrimitiveObject[] compareData = new PrimitiveObject[]{ new ByteObj( (byte)15 ) , new ShortObj( (short)15 ) , new IntegerObj( 15 ) , new LongObj( 15 ) , new FloatObj( 15.0f ) , new DoubleObj( 15.0d ) };
    for( PrimitiveObject obj : compareData ){
      IFilter filter = new NumberFilter( NumberFilterType.LE , obj );
      boolean[] filterResult = new boolean[10];
      filterResult = column.filter( filter , filterResult );
      if( filterResult == null ){
        assertTrue( true );
        return;
      }
      for( int i = 0 ; i < mustReadIndex.length ; i++ ){
        assertTrue( filterResult[mustReadIndex[i]] );
      }
    }
  }

  @Test( dataProvider = "target_class" )
  public void T_le_obj_2( final IColumn column ) throws IOException{
    int[] mustReadIndex = { 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 };
    PrimitiveObject[] compareData = new PrimitiveObject[]{ new ByteObj( (byte)16 ) , new ShortObj( (short)16 ) , new IntegerObj( 16 ) , new LongObj( 16 ) , new FloatObj( 16.0f ) , new DoubleObj( 16.0d ) };
    for( PrimitiveObject obj : compareData ){
      IFilter filter = new NumberFilter( NumberFilterType.LE , obj );
      boolean[] filterResult = new boolean[10];
      filterResult = column.filter( filter , filterResult );
      if( filterResult == null ){
        assertTrue( true );
        return;
      }
      for( int i = 0 ; i < mustReadIndex.length ; i++ ){
        assertTrue( filterResult[mustReadIndex[i]] );
      }
    }
  }

  @Test( dataProvider = "target_class" )
  public void T_gt_obj_1( final IColumn column ) throws IOException{
    int[] mustReadIndex = { 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 };
    PrimitiveObject[] compareData = new PrimitiveObject[]{ new ByteObj( (byte)14 ) , new ShortObj( (short)14 ) , new IntegerObj( 14 ) , new LongObj( 14 ) , new FloatObj( 14.0f ) , new DoubleObj( 14.0d ) };
    for( PrimitiveObject obj : compareData ){
      IFilter filter = new NumberFilter( NumberFilterType.GT , obj );
      boolean[] filterResult = new boolean[10];
      filterResult = column.filter( filter , filterResult );
      if( filterResult == null ){
        assertTrue( true );
        return;
      }
      for( int i = 0 ; i < mustReadIndex.length ; i++ ){
        assertTrue( filterResult[mustReadIndex[i]] );
      }
    }
  }

  @Test( dataProvider = "target_class" )
  public void T_ge_obj_1( final IColumn column ) throws IOException{
    int[] mustReadIndex = { 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 };
    PrimitiveObject[] compareData = new PrimitiveObject[]{ new ByteObj( (byte)15 ) , new ShortObj( (short)15 ) , new IntegerObj( 15 ) , new LongObj( 15 ) , new FloatObj( 15 ) , new DoubleObj( 15 ) };
    for( PrimitiveObject obj : compareData ){
      IFilter filter = new NumberFilter( NumberFilterType.GE , obj );
      boolean[] filterResult = new boolean[10];
      filterResult = column.filter( filter , filterResult );
      if( filterResult == null ){
        assertTrue( true );
        return ;
      }
      for( int i = 0 ; i < mustReadIndex.length ; i++ ){
        assertTrue( filterResult[mustReadIndex[i]] );
      }
    }
  }

  @Test( dataProvider = "target_class" )
  public void T_ge_obj_2( final IColumn column ) throws IOException{
    int[] mustReadIndex = { 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 };
    PrimitiveObject[] compareData = new PrimitiveObject[]{ new ByteObj( (byte)14 ) , new ShortObj( (short)14 ) , new IntegerObj( 14 ) , new LongObj( 14 ) , new FloatObj( 14 ) , new DoubleObj( 14 ) };
    for( PrimitiveObject obj : compareData ){
      IFilter filter = new NumberFilter( NumberFilterType.GE , obj );
      boolean[] filterResult = new boolean[10];
      filterResult = column.filter( filter , filterResult );
      if( filterResult == null ){
        assertTrue( true );
        return ;
      }
      for( int i = 0 ; i < mustReadIndex.length ; i++ ){
        assertTrue( filterResult[mustReadIndex[i]] );
      }
    }
  }

}
