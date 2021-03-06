package estg.mtsd.bikeshare.payment.service.service.impl;

import estg.mtsd.bikeshare.payment.service.dao.PaymentDao;
import estg.mtsd.bikeshare.payment.service.entity.Payment;
import estg.mtsd.bikeshare.payment.service.producers.PaymentProducer;
import estg.mtsd.bikeshare.payment.service.service.PaymentService;
import estg.mtsd.bikeshare.shared.library.vo.PaymentDataVo;
import estg.mtsd.bikeshare.shared.library.vo.PaymentVo;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDao paymentDao;

    @Autowired
    PaymentProducer paymentProducer;

    @Override
    @Transactional
    public void save(PaymentDataVo paymentDataVo) {
        Integer id = paymentDataVo.getPaymentId();
        Optional<Payment> paymentOptional = paymentDao.findById(id);
        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            payment.setStatus(PaymentVo.VALIDATING_PAYMENT);
            payment.setCompany(paymentDataVo.getCompany());
            payment.setName(paymentDataVo.getName());
            payment.setTaxNumber(paymentDataVo.getTaxNumber());

            payment = paymentDao.save(payment);

            PaymentVo paymentVo = new PaymentVo();
            BeanUtils.copyProperties(payment, paymentVo);

            paymentProducer.send(paymentVo);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    @Transactional
    public void update(PaymentVo paymentVo) {
        Integer id = paymentVo.getId();
        boolean objectExists = paymentDao.existsById(id);
        if (objectExists) {
            Payment payment = new Payment();
            BeanUtils.copyProperties(paymentVo, payment);
            paymentDao.save(payment);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        boolean objectExists = paymentDao.existsById(id);
        if (objectExists) {
            paymentDao.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    @Transactional
    public PaymentVo get(Integer id) {
        Optional<Payment> paymentOptional = paymentDao.findById(id);
        PaymentVo paymentVo;
        if (paymentOptional.isPresent()) {
            paymentVo = new PaymentVo();
            BeanUtils.copyProperties(paymentOptional.get(), paymentVo);
        } else {
            throw new EntityNotFoundException();
        }

        return paymentVo;
    }

    @Override
    @Transactional
    public Page<PaymentVo> getAll(Pageable pageable, String userEmail) {
        Page<Payment> entities = paymentDao.findAllByUserEmail(pageable, userEmail);

        return entities.map(this::convertToPaymentVo);
    }

    @Override
    public PaymentVo getByRentalId(Integer rentalId) {
        Optional<Payment> paymentOptional = paymentDao.findByRentalId(rentalId);
        PaymentVo paymentVo;
        if (paymentOptional.isPresent()) {
            paymentVo = new PaymentVo();
            BeanUtils.copyProperties(paymentOptional.get(), paymentVo);
        } else {
            throw new EntityNotFoundException();
        }

        return paymentVo;
    }

    private PaymentVo convertToPaymentVo(Payment entity) {
        PaymentVo paymentVo = new PaymentVo();
        BeanUtils.copyProperties(entity, paymentVo);

        return paymentVo;
    }

}
