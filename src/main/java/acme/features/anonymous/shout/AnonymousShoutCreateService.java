package acme.features.anonymous.shout;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.entities.CustomisationParameter;
import acme.framework.entities.Shout;
import acme.framework.services.AbstractCreateService;
import acme.framework.utilities.SpamDetect;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	@Autowired
	protected AnonymousShoutRepository repository;
	
	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "author", "text", "info");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;
		
		Shout result;
		Date moment;

		moment = Date.from(Instant.now());
		
		result = new Shout();
		result.setAuthor("");
		result.setText("");
		result.setMoment(moment);
		result.setInfo("");
		
		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;
        
        final CustomisationParameter params = this.repository.findSpam().get(0);

        if (!errors.hasErrors("author")) {
            errors.state(request, !SpamDetect.isSpamText(entity.getAuthor(),params), "author", "anonymous.shout.error.spam");
        }

        if (!errors.hasErrors("text")) {
            errors.state(request, !SpamDetect.isSpamText(entity.getText(), params), "text", "anonymous.shout.error.spam");
        }

    }

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;
		
		Date moment;
		
		moment = Date.from(Instant.now());
		entity.setMoment(moment);
		this.repository.save(entity);
	}
	
}
