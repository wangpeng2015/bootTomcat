<style>
/* 条件最高是宽度为600px，大于600px则不满足条件，不会应用此样式 */
@media only screen and (max-width: 600px) {
.table-responsive table td:nth-child(2),
.table-responsive table td:nth-child(3),
.table-responsive table td:nth-child(5),
.table-responsive table td:nth-child(6),
.table-responsive table td:nth-child(7)
	{display: none;}
}
</style>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div>
	<div class="row">
		<div class="col-lg-6">
			<div class="input-group">
				 <input type="text" class="form-control" placeholder="请输入查询的客户账号" id="customerSearch">
				 <span class="input-group-btn">
					<button class="btn btn-default" type="button" id="searchBtn">查询</button>
				</span>
			</div>
			<!-- /input-group -->
		</div>
		<!-- /.col-lg-6 -->
	</div>
	<div class="container content">
		<h3>客户管理列表--(<a id="count"></a>)人</h3>
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<td>账户</td>
						<td>创建时间</td>
						<td>金额</td>
						<td>到期时间</td>
						<td>到期时间最新修改日期</td>
						<td>推荐码</td>
						<td>设备的uid</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody id="empTable_tbody">
				</tbody>
			</table>
		</div>
		<nav id="navPageNavigation" aria-label="Page navigation" style="float:right">
		  <ul class="pagination">
		    <li>
		      <a href="javascript:void(0)" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    <li><a href="javascript:void(0)">1</a></li>
		    <li><a href="javascript:void(0)">2</a></li>
		    <li><a href="javascript:void(0)">3</a></li>
		    <li><a href="javascript:void(0)">4</a></li>
		    <li><a href="javascript:void(0)">5</a></li>
		    <li><a href="javascript:void(0)">6</a></li>
		    <li><a href="javascript:void(0)">7</a></li>
		    <li><a href="javascript:void(0)">8</a></li>
		    <li><a href="javascript:void(0)">9</a></li>
		    <li><a href="javascript:void(0)">10</a></li>
		    <li>
		      <a href="javascript:void(0)" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		  </ul>
		</nav>
	</div>
</div>