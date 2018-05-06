package com.br.aparecido;

import java.util.Calendar;

public class MonthlyDateManager extends DateManager {

	public MonthlyDateManager() {
		super();

		Calendar startDate = getStartDate();
		startDate.set(Calendar.DAY_OF_MONTH, 1);

		setStartDate(startDate);

		Calendar finalDate = getFinalDate();
		finalDate.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));

		setFinalDate(finalDate);
	}

	@Override
	public boolean canGoToNextDate() {
		Calendar finalDate = Calendar.getInstance();

		finalDate.clear(Calendar.MINUTE);
		finalDate.clear(Calendar.SECOND);
		finalDate.clear(Calendar.MILLISECOND);
		finalDate.set(Calendar.HOUR_OF_DAY, 0);
		finalDate.set(Calendar.DAY_OF_MONTH, finalDate.getActualMaximum(Calendar.DAY_OF_MONTH));

		return getFinalDate().compareTo(finalDate) < 0;
	}

	@Override
	public boolean canGoToPreviousDate() {
		return getStartDate().compareTo(getMinDateSelectable()) > 0;
	}

	@Override
	public void goToNextDate() throws DateManagerException {
		if (canGoToNextDate()) {

			getStartDate().add(Calendar.MONTH, 1);
			getFinalDate().add(Calendar.MONTH, 1);
			getFinalDate().set(Calendar.DAY_OF_MONTH, getFinalDate().getActualMaximum(Calendar.DAY_OF_MONTH));

		} else {
			throw new DateManagerException("Não é possível avançar para uma data além de hoje.");
		}
	}

	@Override
	public void goToPreviousDate() throws DateManagerException {
		if (canGoToPreviousDate()) {

			getStartDate().add(Calendar.MONTH, -1);
			getFinalDate().add(Calendar.MONTH, -1);
			getFinalDate().set(Calendar.DAY_OF_MONTH, getFinalDate().getActualMaximum(Calendar.DAY_OF_MONTH));

		} else {
			throw new DateManagerException("Não é possível regredir para uma data anterior a 01/01/1900.");
		}
	}

	@Override
	public void setSelectedDate(Calendar date) throws DateManagerException {

		Calendar dateMaxLimit = Calendar.getInstance();

		dateMaxLimit.clear(Calendar.MINUTE);
		dateMaxLimit.clear(Calendar.SECOND);
		dateMaxLimit.clear(Calendar.MILLISECOND);
		dateMaxLimit.set(Calendar.HOUR_OF_DAY, 0);
		dateMaxLimit.set(Calendar.DAY_OF_MONTH, dateMaxLimit.getActualMaximum(Calendar.DAY_OF_MONTH));

		if (date.compareTo(getMinDateSelectable()) >= 0 && date.compareTo(dateMaxLimit) <= 0) {

			Calendar startDate = getStartDate();
			startDate.set(Calendar.MONTH, date.get(Calendar.MONTH));
			startDate.set(Calendar.YEAR, date.get(Calendar.YEAR));
			setStartDate(startDate);

			Calendar finalDate = getFinalDate();
			finalDate.set(Calendar.MONTH, date.get(Calendar.MONTH));
			finalDate.set(Calendar.YEAR, date.get(Calendar.YEAR));
			finalDate.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
			setFinalDate(finalDate);

		} else {
			throw new DateManagerException("Data Inválida " + date.getTime().toString());
		}
	}
}
