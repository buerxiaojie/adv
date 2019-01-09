Pn = {
	version : '1.0'
};
/**
 * js namespace e.g. Pn.ns('Company', 'Company.data');
 */
Pn.ns = function() {
	var a = arguments, o = null, i, j, d, rt;
	for (i = 0;i < a.length; ++i) {
		d = a[i].split(".");
		rt = d[0];
		eval('if (typeof ' + rt + ' == "undefined"){' + rt + ' = {};} o = '
				+ rt + ';');
		for (j = 1;j < d.length; ++j) {
			o[d[j]] = o[d[j]] || {};
			o = o[d[j]];
		}
	}
};
/**
 * get url parameter
 */
Pn.getParam = function(key) {
	var params = location.search.substr(1).split('&');
	var kv;
	for (var i = 0;i < params.length; i++) {
		kv = params[i].split('=');
		if (kv[0] == key) {
			return kv[1];
		}
	}
};
/**
 * check checkbox.
 * 
 * @param name
 *            string of checkbox name
 * @param checked
 *            boolean of checked
 */
Pn.checkbox = function(name, checked) {
	$("input[type=checkbox][name=" + name + "]").each(function() {
		$(this).attr("checked", checked);
	});
}
/**
 * 复选框选中的个数
 * 
 * @param name
 *            string of checkbox name
 */
Pn.checkedCount = function(name) {
	var batchChecks = document.getElementsByName(name);
	var count = 0;
	for (var i = 0;i < batchChecks.length; i++) {
		if (batchChecks[i].checked) {
			count++;
		}
	}
	return count;
}