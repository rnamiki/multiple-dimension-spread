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
package jp.co.yahoo.dataplatform.mds.inmemory;

import java.io.IOException;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.NullableBitVector;

import jp.co.yahoo.dataplatform.mds.spread.column.ColumnType;

public class ArrowNullMemoryAllocator implements IMemoryAllocator{

  private static final IMemoryAllocator NULL_ALLOCATOR = new ArrowNullMemoryAllocator();

  private ArrowNullMemoryAllocator(){}

  public static IMemoryAllocator getInstance(){
    return NULL_ALLOCATOR;
  }

  @Override
  public void setNull( final int index ){
  }

  @Override
  public void setBoolean( final int index , final boolean value ) throws IOException{
  }

  @Override
  public void setByte( final int index , final byte value ) throws IOException{
  }

  @Override
  public void setShort( final int index , final short value ) throws IOException{
  }

  @Override
  public void setInteger( final int index , final int value ) throws IOException{
  }

  @Override
  public void setLong( final int index , final long value ) throws IOException{
  }

  @Override
  public void setFloat( final int index , final float value ) throws IOException{
  }

  @Override
  public void setDouble( final int index , final double value ) throws IOException{
  }

  @Override
  public void setBytes( final int index , final byte[] value ) throws IOException{
  }

  @Override
  public void setBytes( final int index , final byte[] value , final int start , final int length ) throws IOException{
  }

  @Override
  public void setString( final int index , final String value ) throws IOException{
  }

  @Override
  public void setString( final int index , final char[] value ) throws IOException{
  }

  @Override
  public void setString( final int index , final char[] value , final int start , final int length ) throws IOException{
  }

  @Override
  public void setArrayIndex( final int index , final int start , final int length ) throws IOException{
  }

  @Override
  public void setValueCount( final int count ) throws IOException{
  }

  @Override
  public int getValueCount() throws IOException{
    return 0;
  }

  @Override
  public IMemoryAllocator getChild( final String columnName , final ColumnType type ) throws IOException{
    return NULL_ALLOCATOR;
  }

}
