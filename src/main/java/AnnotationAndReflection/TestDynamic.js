function f() {
    // 运行过程中，改变x的类型
    var x = "var a=3;var b=5;alert(a+b)";
    eval(x)


}