package com.br.aparecido;

import java.util.Calendar;

public class YearlyDateManager extends DateManager {

	public YearlyDateManager() {
		super();

		Calendar startDate = getStartDate();
		startDate.set(Calendar.MONTH, 0);
		startDate.set(Calendar.DAY_OF_MONTH, 1);

		setStartDate(startDate);

		Calendar finalDate = getFinalDate();
		finalDate.set(Calendar.MONTH, finalDate.getActualMaximum(Calendar.MONTH));
		finalDate.set(Calendar.DAY_OF_MONTH, finalDate.getActualMaximum(Calendar.DAY_OF_MONTH));

		setFinalDate(finalDate);
	}

	@Override
	public boolean canGoToNextDate() {
		return getFinalDate().get(Calendar.YEAR) < Calendar.getInstance().get(Calendar.YEAR);
	}

	@Override
	public boolean canGoToPreviousDate() {
		return getStartDate().get(Calendar.YEAR) > getMinDateSelectable().get(Calendar.YEAR);
	}

	@Override
	public void goToNextDate() throws DateManagerException {
		if (canGoToNextDate()) {

			getStartDate().add(Calendar.YEAR, 1);
			getFinalDate().add(Calendar.YEAR, 1);

		} else {
			throw new DateManagerException("Não é possível avançar para uma data além de hoje.");
		}

	}

	@Override
	public void goToPreviousDate() throws DateManagerException {

		if (canGoToPreviousDate()) {

			getStartDate().add(Calendar.YEAR, -1);
			getFinalDate().add(Calendar.YEAR, -1);

		} else {
			throw new DateManagerException("Não é possível regredir para uma data anterior a 01/01/1900.");
		}

	}

	@Override
	public void setSelectedDate(Calendar date) throws DateManagerException {
		if (date.get(Calendar.YEAR) <= Calendar.getInstance().get(Calendar.YEAR)
				&& date.get(Calendar.YEAR) >= getMinDateSelectable().get(Calendar.YEAR)) {

			getStartDate().set(Calendar.YEAR, date.get(Calendar.YEAR));
			getFinalDate().set(Calendar.YEAR, date.get(Calendar.YEAR));
		} else {
			throw new DateManagerException("Data Inválida " + date.getTime().toString());
		}
	}
}
