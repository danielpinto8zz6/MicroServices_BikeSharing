package estg.mtsd.bikeshare.payment.service.service.impl;

import estg.mtsd.bikeshare.payment.service.dao.PaymentDao;
import estg.mtsd.bikeshare.payment.service.entity.Payment;
import estg.mtsd.bikeshare.payment.service.service.PaymentService;
import estg.mtsd.bikeshare.shared.library.vo.PaymentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentDao paymentDao;

	@Override
	@Transactional
	public void save(PaymentVo paymentVo) {
		Integer id = paymentVo.getId();
		boolean objectAlreadyExists=paymentDao.existsById(id);
		if(!objectAlreadyExists) {
			Payment payment = new Payment();
			BeanUtils.copyProperties(paymentVo, payment);
			paymentDao.save(payment);
		}else {
			throw new EntityExistsException();
		}
		
	}

	@Override
	@Transactional
	public void update(PaymentVo paymentVo) {
		Integer id = paymentVo.getId();
		boolean objectExists=paymentDao.existsById(id);
		if(objectExists) {
			Payment payment = new Payment();
			BeanUtils.copyProperties(paymentVo, payment);
			paymentDao.save(payment);
		}else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		boolean objectExists=paymentDao.existsById(id);
		if(objectExists) {
			paymentDao.deleteById(id);
		}else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	@Transactional
	public PaymentVo get(Integer id) {
		Optional<Payment> paymentOptional = paymentDao.findById(id);
		PaymentVo paymentVo;
		if(paymentOptional.isPresent()) {
			paymentVo = new PaymentVo();
			BeanUtils.copyProperties(paymentOptional.get(), paymentVo);
		}else {
			throw new EntityNotFoundException();
		}

		return paymentVo;
	}

	@Override
	@Transactional
	public List<PaymentVo> getAll() {
		List<Payment> paymentList = paymentDao.findAll();
		List<PaymentVo> paymentVoList = new ArrayList<>();
		if (!paymentList.isEmpty()) {
			for (Payment payment : paymentList) {
				PaymentVo paymentVo = new PaymentVo();
				BeanUtils.copyProperties(payment, paymentVo);
				paymentVoList.add(paymentVo);
			}
		}
		return paymentVoList;
	}

}

