package com.example.marketsystem.service;

import com.example.marketsystem.entity.*;
import com.example.marketsystem.entity.template.PayType;
import com.example.marketsystem.entity.template.RoleName;
import com.example.marketsystem.exception.GenericNotFoundException;
import com.example.marketsystem.payload.ApiResponse;
import com.example.marketsystem.payload.PaymentDto;
import com.example.marketsystem.payload.ResPageable;
import com.example.marketsystem.repository.*;
import com.example.marketsystem.utils.CommonUtils;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CompanyRepository companyRepository;
    private final PayRepository payRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;

    public ApiResponse<?> save(PaymentDto paymentDto) {
        User user = userRepository.findById(paymentDto.getId()).orElseThrow(() -> GenericNotFoundException.builder().message("User not found").statusCode(404).build());
        Company company = companyRepository.findById(1L).orElseThrow(() -> GenericNotFoundException.builder().message("Company not found").statusCode(404).build());
        List<Order> orders = new ArrayList<>();
        List<Pay> pays = new ArrayList<>();
        var summa = 0.0;
        var clientTotalPrice = 0.0;
        paymentDto.getOrders().forEach(order -> {
           orders.add(orderRepository.save( Order.builder()
                    .product(order.getProduct())
                    .measure(order.getMeasure())
                    .measureCount(order.getMeasureCount())
                    .totalPrice(order.getProduct().getPrice() * order.getMeasureCount())
                    .build()));
        });
        paymentDto.getPays().forEach(pay -> {
            pays.add(payRepository.save( Pay.builder()
                            .payType(pay.getPayType())
                            .price(pay.getPrice())
                    .build()));
        });
        for (Order order : orders) {
             summa += order.getTotalPrice();
            Product product = order.getProduct();
            product.setMeasureCount(product.getMeasureCount()-order.getMeasureCount());
            productRepository.save(product);
        }
        for (Pay pay : pays) {
            clientTotalPrice += pay.getPrice();
        }
        double clientDebt = summa - clientTotalPrice;
        user.setDebit(clientDebt);
        userRepository.save(user);
        Payment.builder()
                .user(user)
                .orders(orders)
                .pays(pays)
                .company(company)
                .summa(summa)
                .build();
        return ApiResponse.builder().message("success").build();
    }

    public ApiResponse<?> getPagePayments(int page, int size) throws Exception {
        Page<Payment> payments = paymentRepository.findAll(CommonUtils.getPageable(page, size));
        if (!payments.isEmpty()) {
            return ApiResponse.builder().body(ResPageable.builder().page(page).size(size).totalPage(payments.getTotalPages())
                    .totalElements(payments.getTotalElements())
                    .object(new ArrayList<>(payments.stream().map(this::getPaymentDto).toList())).build()).message("Success").build();
        } else return ApiResponse.builder().message("Product list empty").success(false).build();
    }

    public PaymentDto getPaymentDto(Payment payment){
        return PaymentDto.builder()
                .id(payment.getId())
                .user(payment.getUser())
                .orders(payment.getOrders())
                .pays(payment.getPays())
                .company(payment.getCompany())
                .build();
    }



    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontParagraph.setSize(18);

        Paragraph paragraph = new Paragraph("xozirgi vaqt", fontParagraph);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);
        document.close();
    }
}
