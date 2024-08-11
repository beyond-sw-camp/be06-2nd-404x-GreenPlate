package com.team404x.greenplate.orders.repository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team404x.greenplate.common.support.Querydsl4RepositorySupport;
import com.team404x.greenplate.company.model.entity.QCompany;
import com.team404x.greenplate.item.model.entity.QItem;
import com.team404x.greenplate.orders.model.entity.*;
import com.team404x.greenplate.orders.model.requset.OrderSearchListReq;
import com.team404x.greenplate.orders.model.requset.OrderSearchReq;
import com.team404x.greenplate.user.address.entity.QAddress;
import com.team404x.greenplate.user.model.entity.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


import static com.querydsl.core.group.GroupBy.sum;
import static com.team404x.greenplate.company.model.entity.QCompany.company;
import static com.team404x.greenplate.item.model.entity.QItem.*;
import static com.team404x.greenplate.orders.model.entity.QOrderDetail.orderDetail;
import static com.team404x.greenplate.orders.model.entity.QOrders.orders;

@Repository
public class OrderQueryRepository extends Querydsl4RepositorySupport {

    private final JPAQueryFactory queryFactory;


    public OrderQueryRepository(JPAQueryFactory queryFactory) {
        super(Orders.class);
        this.queryFactory = queryFactory;
    }

    public Page<OrdersQueryProjection> getOrdersUser(Long userId, Pageable pageable) {
        QOrders orders = QOrders.orders;
        QOrderDetail orderDetail = QOrderDetail.orderDetail;
        QItem item = QItem.item;
        QUser user = QUser.user;

        // Create dynamic conditions
        BooleanBuilder whereBuilder = new BooleanBuilder();
        whereBuilder.and(user.id.eq(userId));

        return applyPagination(pageable, queryFactory -> {
            return queryFactory
                    .select(new QOrdersQueryProjection(
                            orders.id,
                            item.id,
                            item.name,
                            orderDetail.price.multiply(orderDetail.cnt).sum(),
                            orderDetail.count().intValue(),
                            orders.orderDate,
                            orders.orderState,
                            orders.refundYn
                    ))
                    .from(orders)
                    .leftJoin(orderDetail).on(orderDetail.orders.eq(orders))
                    .leftJoin(item).on(orderDetail.item.eq(item))
                    .where(whereBuilder)
                    .groupBy(orders.id);
        });
    }

    public List<OrderDetailQueryProjection> getOrderDetailUser(Long orderId) {
        QOrders orders = QOrders.orders;
        QOrderDetail orderDetail = QOrderDetail.orderDetail;
        QItem item = QItem.item;

        return queryFactory
                .select(new QOrderDetailQueryProjection(
                        orders.id,
                        item.id,
                        item.name,
                        orderDetail.price,
                        orderDetail.cnt,
                        orders.orderDate,
                        orders.orderState,
                        orders.refundYn,
                        orders.zipCode,
                        orders.address,
                        orders.phoneNum,
                        orders.invoice
                ))
                .from(orders)
                .leftJoin(orderDetail).on(orderDetail.orders.eq(orders))
                .leftJoin(item).on(orderDetail.item.eq(item))
                .where(orders.id.eq(orderId))
                .fetch();
    }

    public Page<OrdersQueryProjection> getOrders(Long companyId, OrderSearchListReq searchReq, Pageable pageable) {
        QOrders orders = QOrders.orders;
        QOrderDetail orderDetail = QOrderDetail.orderDetail;
        QItem item = QItem.item;
        QCompany company = QCompany.company;

        // Create dynamic conditions
        BooleanBuilder whereBuilder = new BooleanBuilder();
        whereBuilder.and(company.id.eq(companyId));
        if (searchReq.getStatus() != null) {
            whereBuilder.and(orders.orderState.eq(searchReq.getStatus()));
        }

        return applyPagination(pageable, queryFactory -> {
            return queryFactory
                    .select(new QOrdersQueryProjection(
                            orders.id,
                            item.id,
                            item.name,
                            orderDetail.price.multiply(orderDetail.cnt).sum(),
                            orderDetail.count().intValue(),
                            orders.orderDate,
                            orders.orderState,
                            orders.refundYn
                    ))
                    .from(orders)
                    .leftJoin(orderDetail).on(orderDetail.orders.eq(orders))
                    .leftJoin(item).on(orderDetail.item.eq(item))
                    .leftJoin(company).on(company.eq(item.company))
                    .where(whereBuilder)
                    .groupBy(orders.id);
        });
    }

    public List<OrderDetailQueryProjection> getOrderDetail(Long companyId, Long orderId) {
        QOrders orders = QOrders.orders;
        QOrderDetail orderDetail = QOrderDetail.orderDetail;
        QItem item = QItem.item;
        QCompany company = QCompany.company;

        return queryFactory
                .select(new QOrderDetailQueryProjection(
                        orders.id,
                        item.id,
                        item.name,
                        orderDetail.price,
                        orderDetail.cnt,
                        orders.orderDate,
                        orders.orderState,
                        orders.refundYn,
                        orders.zipCode,
                        orders.address,
                        orders.phoneNum,
                        orders.invoice
                ))
                .from(orders)
                .leftJoin(orderDetail).on(orderDetail.orders.eq(orders))
                .leftJoin(item).on(orderDetail.item.eq(item))
                .leftJoin(company).on(company.eq(item.company))
                .where(company.id.eq(companyId), orders.id.eq(orderId))
                .fetch();
    }

}
