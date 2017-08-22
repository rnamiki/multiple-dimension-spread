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
package jp.co.yahoo.dataplatform.mds.block;


public class BlockReadOffset implements Comparable<BlockReadOffset>{

  public final int start;
  public final int length;
  public final byte[] buffer;

  public BlockReadOffset( final int start , final int length , final byte[] buffer ){
    this.start = start;
    this.length = length;
    this.buffer = buffer;
  }

  @Override
  public int compareTo( final BlockReadOffset target ){
    if( this.start > target.start ){
      return 1;
    }
    else if( this.start < target.start ){
      return -1;
    }

    return 0;
  }

}
