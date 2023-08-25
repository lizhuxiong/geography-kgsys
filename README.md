# geography-kgsys
后端架构
# 1 后端项目架构

## 1.1 DAO层

DAO层是数据访问层，也是通过Mybatis逆向工程生成的mapper层。Dao层与数据库直接打交道，实现数据访问的工作。在数据接收上，DAO层会调用Domain中的各个PO实体对象，用于封装数据库数据。具体实现如下：

1）创建mapper对应SQL的mapper.xml

2）写mapper接口

## 1.2 Service层

Service层是业务逻辑层，调用DAO层完成特定业务的操作。具体结构实现如下：

1）设计service接口

2）设计实现service接口的sericeImpl类

3）sericeImpl类中方法调用DAO层实现具体业务。

## 1.3 Controller层

Controller层为前端提供HTTP访问入口，并向前端返回数据。Controller不用关心具体的业务逻辑，具体的业务逻辑放在Service层，Controller只需调用它封装好的方法即可。具体实现：

1）调用sericeImpl中的各个业务方法

2）调用utils工具类中的某些方法。



## 1.4 Domain

Domain是项目的一个子模块，对应PO、VO和resp等JavaBean实体类，主要用于封装数据。

1）PO与数据库Domain层查询数据一一对应

2）VO作为Controller层向前端返回数据的封装实体

resp会对封装实体数据做进一步的封装（包括每次http请求的响应状态码等信息）

