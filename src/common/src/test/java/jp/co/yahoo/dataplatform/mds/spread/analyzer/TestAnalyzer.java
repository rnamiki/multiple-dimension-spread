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

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import jp.co.yahoo.dataplatform.schema.objects.*;

import jp.co.yahoo.dataplatform.mds.spread.Spread;
import jp.co.yahoo.dataplatform.mds.spread.column.NullColumn;

public class TestAnalyzer {

  @Test
  public void T_getAnalizer_1() throws IOException{
    Spread spread = new Spread();

    Map<String,Object> data = new HashMap<String,Object>();
    data.put( "a" , new LongObj( (long)100 ) );

    spread.addRow( data );
    spread.addRow( data );
    spread.addRow( data );
    spread.addRow( data );

    List<IColumnAnalizer> result = Analyzer.getAnalizer( spread );
    assertEquals( result.size() , 1 );
  }

  @Test
  public void T_analize_1() throws IOException{
    Spread spread = new Spread();

    Map<String,Object> data = new HashMap<String,Object>();
    data.put( "a" , new LongObj( (long)100 ) );

    spread.addRow( data );
    spread.addRow( data );
    spread.addRow( data );
    spread.addRow( data );

    List<IColumnAnalizeResult> result = Analyzer.analize( spread );
    assertEquals( result.size() , 1 );
  }

}
