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
package jp.co.yahoo.dataplatform.mds.spread.analyzer;

import java.io.IOException;

import java.util.Set;
import java.util.HashSet;

import jp.co.yahoo.dataplatform.mds.spread.column.ICell;
import jp.co.yahoo.dataplatform.mds.spread.column.PrimitiveCell;
import jp.co.yahoo.dataplatform.mds.spread.column.IColumn;
import jp.co.yahoo.dataplatform.mds.spread.column.ColumnType;

public class StringColumnAnalizer implements IColumnAnalizer{

  private final IColumn column;

  public StringColumnAnalizer( final IColumn column ){
    this.column = column;
  }

  public IColumnAnalizeResult analize() throws IOException{
    boolean maybeSorted = true;
    String currentSortCheckValue = "";
    int nullCount = 0;
    int rowCount = 0;
    int totalLogicalDataSize = 0;
    int totalUtf8ByteSize = 0;
    int uniqLogicalDataSize = 0;
    int uniqUtf8ByteSize = 0;

    int minCharLength = Integer.MAX_VALUE;
    int maxCharLength = 0;
    int minUtfBytes = Integer.MAX_VALUE;
    int maxUtfBytes = 0;

    int startIndex = -1;
    int lastIndex = 0;
    Set<String> dicSet = new HashSet<String>();

    String min = "";
    String max = "";
    for( int i = 0 ; i < column.size() ; i++ ){
      ICell cell = column.get(i);
      if( cell.getType() == ColumnType.NULL ){
        nullCount++;
        continue;
      }
      String target = ( (PrimitiveCell) cell ).getRow().getString();
      if( maybeSorted && currentSortCheckValue.compareTo( target ) <= 0 ){
        currentSortCheckValue = target;
      }
      else{
        maybeSorted = false;
      }

      if( startIndex == -1 ){
        startIndex = i;
      }
      lastIndex = i;

      byte[] stringBytes = target.getBytes( "UTF-8" );
      rowCount++;
      int charLength = target.length() * Character.BYTES;
      totalLogicalDataSize += charLength;
      totalUtf8ByteSize += stringBytes.length;

      if( ! dicSet.contains( target ) ){
        uniqLogicalDataSize += charLength;
        uniqUtf8ByteSize += stringBytes.length;
        dicSet.add( target );
        if( min.isEmpty() || 0 < min.compareTo( target ) ){
          min = String.valueOf( target );
        }
        if( max.compareTo( target ) < 0 ){
          max = String.valueOf( target );
        }
        if( charLength < minCharLength ){
          minCharLength = charLength;
        }
        if( maxCharLength < charLength ){
          maxCharLength = charLength;
        }
        if( stringBytes.length < minUtfBytes ){
          minUtfBytes = stringBytes.length;
        }
        if( maxUtfBytes < stringBytes.length ){
          maxUtfBytes = stringBytes.length;
        }
      }
    }

    int uniqCount = dicSet.size();

    return new StringColumnAnalizeResult( column.getColumnName() , column.size() , maybeSorted , nullCount , rowCount , uniqCount , totalLogicalDataSize , startIndex , lastIndex , totalUtf8ByteSize , uniqLogicalDataSize , uniqUtf8ByteSize , minCharLength , maxCharLength , minUtfBytes , maxUtfBytes , min , max );
  }

}
