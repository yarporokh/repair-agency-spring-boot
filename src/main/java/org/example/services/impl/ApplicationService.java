package org.example.services.impl;

import org.example.models.Application;
import org.example.models.Text;
import org.example.models.User;
import org.example.repository.ApplicationRepository;
import org.example.services.IApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.example.utils.ApplicationConstants.*;

@Service
public class ApplicationService implements IApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, ModelMapper modelMapper) {
        this.applicationRepository = applicationRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<Application> findAllUserApplications(User author) {
        List<Application> applicationList = applicationRepository.findApplicationByAuthor(author);
        return applicationList;
    }

    @Override
    @Transactional
    public void newApp(String text, User author) {
        Application application = new Application();
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        Text t = new Text();
        t.setText(text);
        application.setText(t);
        application.setAuthor(author);
        application.setProgress(PROGRESS_NOT_STARTED);
        application.setPaymentStatus(PAYMENT_STATUS_EXPECTED);
        application.setDate(date);
        application.setPrice(BASE_PRICE);

        applicationRepository.save(application);
    }

    @Override
    public List<Application> findAll() {
        return (List<Application>) applicationRepository.findAll();
    }

    @Override
    public void save(Application application) {
        applicationRepository.save(application);
    }
}
