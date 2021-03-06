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

import java.util.Map;
import java.util.HashMap;

public class ColumnNameNode{

  public final String currentNodeName;
  public final Map<String,ColumnNameNode> childNodeMap;
  public boolean isDisableFlag;
  public boolean needChild;

  public ColumnNameNode( final String currentNodeName ){
    this.currentNodeName = currentNodeName;
    childNodeMap = new HashMap<String,ColumnNameNode>();
  }

  public ColumnNameNode( final String currentNodeName , final boolean isDisableFlag ){
    this.currentNodeName = currentNodeName;
    childNodeMap = new HashMap<String,ColumnNameNode>();
    this.isDisableFlag = isDisableFlag;
  }

  public void addChild( final ColumnNameNode childNode ){
    childNodeMap.put( childNode.getNodeName() , childNode );
  }

  public void setNeedAllChild( final boolean needChild ){
    this.needChild = needChild;
  }

  public boolean isNeedAllChild(){
    return needChild;
  }

  public String getNodeName(){
    return currentNodeName;
  }

  public boolean containsChild( final String childNodeName ){
    return childNodeMap.containsKey( childNodeName );
  }

  public boolean isChildEmpty(){
    return childNodeMap.isEmpty();
  }

  public ColumnNameNode getChild( final String childNodeName ){
    return childNodeMap.get( childNodeName );
  } 

  public int getChildSize(){
    return childNodeMap.size();
  }

  public boolean isDisable(){
    return isDisableFlag;
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append( currentNodeName );
    sb.append( "\n" );
    for( Map.Entry<String,ColumnNameNode> entry : childNodeMap.entrySet() ){
      String childNode = entry.getValue().toString( 1 );
      sb.append( "|-" );
      sb.append( childNode );
      sb.append( "\n" );
    }
    return new String( sb );
  }

  public String toString( final int depth ){
    StringBuilder sbSpace = new StringBuilder();
    for( int i = 0 ; i < depth ; i++ ){
      sbSpace.append( " " );
    }
    String space = new String( sbSpace );
    StringBuilder sb = new StringBuilder();
    sb.append( space );
    sb.append( currentNodeName );
    for( Map.Entry<String,ColumnNameNode> entry : childNodeMap.entrySet() ){
      String childNode = entry.getValue().toString( depth + 1 );
      sb.append( "\n" );
      sb.append( space );
      sb.append( "|-" );
      sb.append( childNode );
    }
    return new String( sb );
  }

}
